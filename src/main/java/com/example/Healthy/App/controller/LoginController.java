package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.LoginRequestDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.dto.response.LoginResponse;
import com.example.Healthy.App.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

  private UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public BaseResponse<LoginResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
    LoginResponse data = userService.loginUser(loginRequestDto);

    return BaseResponse.<LoginResponse>builder()
        .status(HttpStatus.OK.value())
        .error(false)
        .message("Login successfully")
        .data(data)
        .build();
  }
}