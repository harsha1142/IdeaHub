package com.ipd.demo.service;
import com.ipd.demo.enums.IdeaStatus;
import com.ipd.demo.dto.request.IdeaCreateRequestDTO;
import com.ipd.demo.dto.request.IdeaStatusUpdateRequestDTO;
import com.ipd.demo.dto.request.IdeaUpdateRequestDTO;
import com.ipd.demo.dto.response.IdeaResponseDTO;
import com.ipd.demo.dto.response.IdeaSummaryResponseDTO;
import com.ipd.demo.entity.Idea;
import com.ipd.demo.entity.Users;
import com.ipd.demo.exception.ResourceNotFoundException;
import com.ipd.demo.mapper.IdeaMapper;
import com.ipd.demo.repository.IdeaRepository;
import com.ipd.demo.repository.UsersRepository;
import com.ipd.demo.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.ipd.demo.exception.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;



@Service
@Transactional
public class IdeaServiceImpl implements IdeaService {

    private final IdeaRepository ideaRepository;
    private final UsersRepository usersRepository;
    private final VoteRepository voteRepository;

    public IdeaServiceImpl(IdeaRepository idearepository, UsersRepository usersrepository, VoteRepository voterepository) {
        this.ideaRepository = idearepository;
        this.usersRepository = usersrepository;
        this.voteRepository = voterepository;
    }

    // create idea
    @Override
    public IdeaResponseDTO createIdea(IdeaCreateRequestDTO request) {
        System.out.println(">>> createIdea called with userId = " + request.getUserId());
        Users user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Idea idea = IdeaMapper.toEntity(request);
        idea.setCreatedBy(user);

        Idea savedIdea = ideaRepository.save(idea);
        Long voteCount = voteRepository.countByIdea_Id(savedIdea.getId());

        return IdeaMapper.toResponseDto(savedIdea, voteCount);
    }


    // get all ideas
    @Override
    public List<IdeaSummaryResponseDTO> getAllIdeas() {
        return ideaRepository.findAll()
                .stream()
                .map(idea -> IdeaMapper.toSummaryDto(idea, voteRepository.countByIdea_Id(idea.getId())))
                .collect(Collectors.toList());
    }


    // getting idea by id
    @Override
    public IdeaResponseDTO getIdeaById(Long ideaId) {
        Idea idea = ideaRepository.findById(ideaId).orElseThrow(() -> new ResourceNotFoundException("Idea not found"));

        long voteCount = voteRepository.countByIdea_Id(ideaId);
        return IdeaMapper.toResponseDto(idea, voteCount);
    }


    //updating idea (title and description)
    @Override
    public void updateIdea(Long ideaId, Long userId, IdeaUpdateRequestDTO request) {

        Idea idea = ideaRepository.findById(ideaId)
                .orElseThrow(() -> new ResourceNotFoundException("Idea not found"));

        if (!idea.getCreatedBy().getId().equals(userId)) {
            throw new AccessDeniedException("Not authorized to edit this idea");
        }


        idea.setTitle(request.getTitle());
        idea.setDescription(request.getDescription());

        ideaRepository.save(idea);

    }

    //updating status
    @Override
    public void updateIdeaStatus(Long ideaId, IdeaStatusUpdateRequestDTO request) {
        Idea idea = ideaRepository.findById(ideaId).orElseThrow(() -> new ResourceNotFoundException("Idea not found "));

        if (idea.getStatus() == IdeaStatus.REJECTED) {
            throw new IllegalStateException("Rejected ideas cannot change status");
        }
        idea.setStatus(request.getStatus());
        ideaRepository.save(idea);

    }

    @Override
    public void deleteIdea(Long ideaId, Long userId) {
        Idea idea = ideaRepository.findById(ideaId)
                .orElseThrow(() -> new ResourceNotFoundException("Idea not found"));

        if (idea.getCreatedBy() == null) {
            throw new IllegalStateException("Idea has no creator");
        }


        if (!idea.getCreatedBy().getId().equals(userId)) {

            throw new AccessDeniedException("Not authorized to delete this idea");
        }
        voteRepository.deleteByIdea_Id(ideaId);
        ideaRepository.delete(idea);

    }

}
