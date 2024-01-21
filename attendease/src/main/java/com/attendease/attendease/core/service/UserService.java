package com.attendease.attendease.core.service;

import com.attendease.attendease.core.dto.UserDto;
import com.attendease.attendease.model.Users;

public interface UserService {
    Users save(UserDto userDto);
}
