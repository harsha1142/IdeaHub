package com.ipd.demo.mapper;

import com.ipd.demo.enums.IdeaStatus;
import com.ipd.demo.dto.request.IdeaCreateRequestDTO;
import com.ipd.demo.dto.response.IdeaResponseDTO;
import com.ipd.demo.dto.response.IdeaSummaryResponseDTO;
import com.ipd.demo.entity.Idea;

public class IdeaMapper {
    private IdeaMapper(){

    }

    public static Idea toEntity(IdeaCreateRequestDTO dto){
        Idea idea = new Idea();
        idea.setTitle(dto.getTitle());
        idea.setDescription(dto.getDescription());
        idea.setStatus(IdeaStatus.OPEN);
        return idea;
    }

    public static IdeaResponseDTO toResponseDto(Idea idea, Long voteCount) {
        IdeaResponseDTO dto = new IdeaResponseDTO();
        dto.setId(idea.getId());
        dto.setTitle(idea.getTitle());
        dto.setDescription(idea.getDescription());
        if (idea.getStatus() != null) {
            dto.setStatus(idea.getStatus().name());
        }
        else{
            dto.setStatus(IdeaStatus.OPEN.name());
        }
        dto.setVoteCount(voteCount);
        return dto;
    }

    public static IdeaSummaryResponseDTO toSummaryDto(Idea idea, Long voteCount) {
        IdeaSummaryResponseDTO dto = new IdeaSummaryResponseDTO();

        dto.setId(idea.getId());
        dto.setTitle(idea.getTitle());
        if (idea.getStatus() != null) {
            dto.setStatus(idea.getStatus().name());
        }
        else {
            dto.setStatus(IdeaStatus.OPEN.name());
        }
        dto.setVoteCount(voteCount);
        dto.setCreatedById(idea.getCreatedBy().getId());
        dto.setCreatedByName(
                idea.getCreatedBy().getFirstName() + " " +
                        idea.getCreatedBy().getLastName()
        );

        return dto;
    }
}
