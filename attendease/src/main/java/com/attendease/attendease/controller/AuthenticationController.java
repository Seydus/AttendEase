package com.attendease.attendease.controller;

import com.attendease.attendease.core.dto.UserDto;
import com.attendease.attendease.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegistrationPage(@ModelAttribute("users")UserDto userDto) {
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("users") UserDto userDto, Model model) {
        userService.save(userDto);
        model.addAttribute("message", "Registered Successfully!");
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute("users")UserDto userDto)
    {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("users") UserDto userDto, Model model)
    {
        return "login";
    }
}
