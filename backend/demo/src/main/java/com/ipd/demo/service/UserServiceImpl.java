package com.ipd.demo.service;

import com.ipd.demo.dto.request.UserCreateRequestDTO;
import com.ipd.demo.dto.response.UserResponseDTO;
import com.ipd.demo.entity.Users;
import com.ipd.demo.mapper.UserMapper;
import com.ipd.demo.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UsersRepository usersrepository;

    public UserServiceImpl(UsersRepository usersrepository){
        this.usersrepository = usersrepository;
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO request){
        Users user = UserMapper.toEntity(request);
        Users savedUser = usersrepository.save(user);
        return UserMapper.toResponseDTO(savedUser);
    }
}
