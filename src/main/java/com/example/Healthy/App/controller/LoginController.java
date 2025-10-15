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
    public ResponseEntity<Map<String , Object>> login(@RequestBody LoginRequestDto loginRequestDto){
        Map<String, Object> response = new HashMap<>();
        response.put("message", userService.loginUser(loginRequestDto));
        response.put("email", loginRequestDto.getEmail());
        return ResponseEntity.ok(response);
    }
}
