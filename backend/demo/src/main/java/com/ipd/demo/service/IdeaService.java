package com.ipd.demo.service;

import com.ipd.demo.dto.request.IdeaCreateRequestDTO;
import com.ipd.demo.dto.request.IdeaStatusUpdateRequestDTO;
import com.ipd.demo.dto.request.IdeaUpdateRequestDTO;
import com.ipd.demo.dto.response.IdeaResponseDTO;
import com.ipd.demo.dto.response.IdeaSummaryResponseDTO;

import java.util.List;

public interface IdeaService {

    IdeaResponseDTO createIdea(IdeaCreateRequestDTO request);
    List<IdeaSummaryResponseDTO> getAllIdeas();
    IdeaResponseDTO getIdeaById(Long ideaId);

    void updateIdea(Long ideaId, Long userId, IdeaUpdateRequestDTO request);

    void updateIdeaStatus(Long ideaId, IdeaStatusUpdateRequestDTO request);

    void deleteIdea(Long ideaId, Long userId);


}

