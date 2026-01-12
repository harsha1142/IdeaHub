package com.ipd.demo.service;

import com.ipd.demo.dto.request.CommentCreateRequestDTO;
import com.ipd.demo.dto.response.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    void addComment(CommentCreateRequestDTO request);
    void updateComment(Long commentId, Long userId, String content);
    void deleteComment(Long commentId, Long userId);

    List<CommentResponseDTO> getCommentsByIdea(Long ideaId);
}
