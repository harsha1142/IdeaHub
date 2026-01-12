package com.ipd.demo.mapper;

import com.ipd.demo.dto.response.CommentResponseDTO;
import com.ipd.demo.entity.Comment;

public class CommentMapper {

    private CommentMapper(){

    }
    public static CommentResponseDTO toResponseDto(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUserName(comment.getUser().getFirstName());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUserId(comment.getUser().getId());
        return dto;
    }
}


