package com.attendease.attendease.core.service;

import com.attendease.attendease.model.Role;
import com.attendease.attendease.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {

    private User user;

    public CustomUserDetail(User user) {
        this.user = user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.toString());
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getFirstName() {
        return user.getFirstname();
    }
    public String getLastName() {
        return user.getLastname();
    }

    public String getEmail()
    {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
