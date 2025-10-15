package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.RegisterDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody RegisterDto registerDto){
        UserDto createdUser = userService.createUser(registerDto);
        Map<String, Object> response = new HashMap();
        response.put("message", "Đăng kí thành công");
        response.put("data", createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    }