package com.attendease.attendease.core.service;

import com.attendease.attendease.core.dto.UserDto;
import com.attendease.attendease.model.User;

public interface UserService {
    User save(UserDto userDto);
}
