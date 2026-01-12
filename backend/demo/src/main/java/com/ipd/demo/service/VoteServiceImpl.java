package com.ipd.demo.service;

import com.ipd.demo.dto.request.VoteRequestDTO;
import com.ipd.demo.entity.Idea;
import com.ipd.demo.entity.Users;
import com.ipd.demo.entity.Vote;
import com.ipd.demo.exception.ResourceNotFoundException;
import com.ipd.demo.repository.IdeaRepository;
import com.ipd.demo.repository.UsersRepository;
import com.ipd.demo.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class VoteServiceImpl implements VoteService{

    private final VoteRepository voterepository;
    private final IdeaRepository idearepository;
    private final UsersRepository usersrepository;

    public VoteServiceImpl(VoteRepository voterepository, IdeaRepository idearepository, UsersRepository usersrepository) {
        this.voterepository = voterepository;
        this.idearepository = idearepository;
        this.usersrepository = usersrepository;
    }

    @Override
    public void vote(VoteRequestDTO request) {

        if (voterepository.existsByIdeaIdAndUserId(request.getIdeaId(), request.getUserId())) {
            throw new IllegalStateException("User already voted");
        }
        Idea idea = idearepository.findById(request.getIdeaId())
                .orElseThrow(()-> new ResourceNotFoundException("Idea not found"));

        Users users = usersrepository.findById(request.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("Idea not found"));

        Vote vote = new Vote();
        vote.setIdea(idea);
        vote.setUser(users);

        voterepository.save(vote);

    }
}
