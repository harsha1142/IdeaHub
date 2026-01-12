package com.ipd.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDTO {
    private Long id;
    private Long userId;
    private String content;
    private String userName;
    private LocalDateTime createdAt;


}
