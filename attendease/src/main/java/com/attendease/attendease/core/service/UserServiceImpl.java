package com.attendease.attendease.core.service;

import com.attendease.attendease.core.dto.UserDto;
import com.attendease.attendease.core.repository.UserRepository;
import com.attendease.attendease.model.User;
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
    public User save(UserDto userDto) {
        User user = new User(
                userDto.getUsername(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getRoleID()
        );

        return userRepository.save(user);
    }
}
