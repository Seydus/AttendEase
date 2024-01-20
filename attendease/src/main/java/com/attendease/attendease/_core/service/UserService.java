package com.attendease.attendease._core.service;

import com.attendease.attendease._core.dto.UserDto;
import com.attendease.attendease.model.User;

public interface UserService {
    User save(UserDto userDto);
}
