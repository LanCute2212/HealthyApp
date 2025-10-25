package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.LoginRequestDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.response.BaseResponse; // Thêm
import com.example.Healthy.App.model.User;
import com.example.Healthy.App.service.UserService;
// import org.apache.coyote.Response; // Xóa
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Thêm
// import org.springframework.http.ResponseEntity; // Xóa
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private UserService userService;
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public BaseResponse<Object> login(@RequestBody LoginRequestDto loginRequestDto){
        String loginMessage = userService.loginUser(loginRequestDto);

        Map<String, Object> data = new HashMap<>();
        data.put("email", loginRequestDto.getEmail());

        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(loginMessage)
                .data(data)
                .build();
    }
}