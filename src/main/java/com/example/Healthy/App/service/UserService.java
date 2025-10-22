package com.example.Healthy.App.service;

import com.example.Healthy.App.dto.LoginRequestDto;
import com.example.Healthy.App.dto.RegisterDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.ProfileForm;
import com.example.Healthy.App.dto.request.UpdateProfileForm;
import java.util.List;

public interface UserService {

  List<UserDto> getAllUsers();

  UserDto getUserByID(Integer id);

  UserDto createUser(RegisterDto registerDto);

  UserDto updateUser(String email, UserDto userDto);

  void deleteUser(Integer id);

  String loginUser(LoginRequestDto loginRequestDto);

  ProfileForm getInforByEmail(String email);

  void updateProfile(String email, UpdateProfileForm form);
}
