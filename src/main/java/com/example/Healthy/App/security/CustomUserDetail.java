package com.example.Healthy.App.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.example.Healthy.App.model.Role;
import com.example.Healthy.App.model.User;
import lombok.Data;

@Data
public class CustomUserDetail implements UserDetails{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetail(Integer id, String username, String password,
                            Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomUserDetail build(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(user.getRole());
        List<GrantedAuthority> authorities = roles.stream()
                // --- SỬA Ở ĐÂY ---
                .map(role -> new SimpleGrantedAuthority(role.getName())) // Đổi từ role.toString() thành role.getName()
                .collect(Collectors.toList());
        return new CustomUserDetail(user.getId(), user.getEmail(), user.getPassword(), authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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