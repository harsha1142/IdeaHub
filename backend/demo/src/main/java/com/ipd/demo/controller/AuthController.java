package com.ipd.demo.controller;

import com.ipd.demo.dto.request.LoginRequestDTO;
import com.ipd.demo.dto.request.RegisterRequestDTO;
import com.ipd.demo.dto.response.LoginResponseDTO;
import com.ipd.demo.entity.Users;
import com.ipd.demo.repository.UsersRepository;
import com.ipd.demo.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;


    public AuthController(UsersRepository usersRepository, JwtUtil jwtUtil) {
        this.usersRepository = usersRepository;
        this.jwtUtil = jwtUtil;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        Users user = usersRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user == null || user.getPassword() == null ||
                !user.getPassword().equals(request.getPassword())) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid email or password");
        }
        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(
                new LoginResponseDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName() + " " + user.getLastName(),
                        token
                )
        );
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {

        if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already registered");
        }

        Users user = new Users();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        usersRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(
                new LoginResponseDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName() + " " + user.getLastName(),
                        token
                )
        );
    }
}
