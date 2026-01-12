package com.ipd.demo.controller;

import com.ipd.demo.dto.request.UserCreateRequestDTO;
import com.ipd.demo.dto.response.UserResponseDTO;
import com.ipd.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody UserCreateRequestDTO request) {



    return ResponseEntity.ok(userService.createUser(request));
    }
}