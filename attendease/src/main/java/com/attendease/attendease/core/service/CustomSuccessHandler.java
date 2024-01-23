package com.attendease.attendease.core.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("OnAuthentication was Successful!");

        var authorities = authentication.getAuthorities();
        var roles = authorities.stream().map(c -> c.getAuthority()).findFirst();

        System.out.println("Roles: " + roles.orElse(""));

        if (roles.orElse("").equals("faculty")) {
            System.out.println("Redirecting to /admin-page");
            response.sendRedirect("/admin-page");
        } else if (roles.orElse("").equals("student")) {
            System.out.println("Redirecting to /user-page");
            response.sendRedirect("/user-page");
        } else {
            System.out.println("Redirecting to /error");
            response.sendRedirect("/error");
        }
    }
}
