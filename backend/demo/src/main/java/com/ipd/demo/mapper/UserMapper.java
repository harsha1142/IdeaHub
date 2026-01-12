package com.ipd.demo.mapper;

import com.ipd.demo.dto.request.UserCreateRequestDTO;
import com.ipd.demo.dto.response.UserResponseDTO;
import com.ipd.demo.entity.Users;

public class UserMapper {

    private UserMapper() {}

    public static Users toEntity(UserCreateRequestDTO dto){
        Users user = new Users();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponseDTO toResponseDTO(Users user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
