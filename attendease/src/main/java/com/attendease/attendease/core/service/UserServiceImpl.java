package com.attendease.attendease.core.service;

import com.attendease.attendease.core.dto.UserDto;
import com.attendease.attendease.core.repository.UserRepository;
import com.attendease.attendease.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Users save(UserDto userDto) {
        Users user = new Users(userDto.getName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getRole());

        return userRepository.save(user);
    }
}
