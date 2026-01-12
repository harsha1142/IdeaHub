package com.ipd.demo.service;

import com.ipd.demo.dto.request.UserCreateRequestDTO;
import com.ipd.demo.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserCreateRequestDTO request);
}
