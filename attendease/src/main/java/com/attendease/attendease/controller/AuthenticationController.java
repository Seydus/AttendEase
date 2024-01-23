package com.attendease.attendease.controller;

import com.attendease.attendease.core.dto.UserDto;
import com.attendease.attendease.core.service.UserService;
import com.attendease.attendease.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthenticationController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @ModelAttribute("roleNames")
    public Role.RoleName[] roleNames() {
        return Role.RoleName.values();
    }


    @GetMapping("/register")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto, Model model) {
        model.addAttribute("roles", Role.RoleName.values());
        return "register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Principal principal) {
        // Check if the user is already authenticated
        if (principal != null) {
            return redirectToUserOrAdminPage(principal);
        }

        return "login";
    }

    private String redirectToUserOrAdminPage(Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return "redirect:/admin-page";
        } else {
            return "redirect:/user-page";
        }
    }

    @GetMapping("/user-page")
    public String userPage(Model model, Principal principal)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        return "user";
    }

    @GetMapping("/admin-page")
    public  String adminPage(Model model, Principal principal)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        return "admin";
    }
}
