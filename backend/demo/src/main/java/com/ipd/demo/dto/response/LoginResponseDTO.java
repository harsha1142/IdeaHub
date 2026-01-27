package com.ipd.demo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO {
    private Long userId;
    private String email;
    private String username;
    private String token;
}
