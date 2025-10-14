package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.LoginRequestDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.model.User;
import com.example.Healthy.App.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private UserService userService;
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(userService.loginUser(loginRequestDto));
    }

}
