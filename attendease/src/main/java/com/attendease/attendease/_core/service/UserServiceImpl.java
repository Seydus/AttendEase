package com.attendease.attendease._core.service;

import com.attendease.attendease._core.dto.UserDto;
import com.attendease.attendease.model.User;
import com.attendease.attendease._core.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole());

        return userRepository.save(user);
    }
}
