package com.example.Healthy.App.service;

import com.example.Healthy.App.dto.LoginRequestDto;
import com.example.Healthy.App.dto.RegisterDto;
import com.example.Healthy.App.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserByID(Integer id);
    UserDto createUser(RegisterDto registerDto);
    UserDto updateUser(Integer id, UserDto userDto);
    void deleteUser(Integer id);
    UserDto loginUser(LoginRequestDto loginRequestDto);
}
