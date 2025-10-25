package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.RegisterDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public BaseResponse<UserDto> registerUser(@Valid @RequestBody RegisterDto registerDto){
        UserDto createdUser = userService.createUser(registerDto);
        return BaseResponse.<UserDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Đăng kí thành công")
                .data(createdUser)
                .build();
    }
}