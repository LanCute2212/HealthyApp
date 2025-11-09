package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.request.LoginRequestDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.dto.response.LoginResponse;
import com.example.Healthy.App.security.CustomUserDetail;
import com.example.Healthy.App.security.JwtUtil;
import com.example.Healthy.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/login")
public class LoginController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserService userService;

  @PostMapping
  public BaseResponse<LoginResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginRequestDto.getEmail(),
                      loginRequestDto.getPassword()
              )
      );

      // 2️⃣ Lưu thông tin người dùng vào SecurityContext
      SecurityContextHolder.getContext().setAuthentication(authentication);
      CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

      // 3️⃣ Sinh JWT và Refresh Token
      String jwtToken = jwtUtil.generateToken(userDetails);

      List<String> roles = userDetails.getAuthorities()
              .stream()
              .map(r -> r.getAuthority())
              .collect(Collectors.toList());

      // 4️⃣ Tạo response
      LoginResponse data = LoginResponse.builder()
              .userId(userDetails.getId())
              .email(userDetails.getUsername())
              .jwtToken(jwtToken)
              .roles(roles)
              .build();

      return BaseResponse.<LoginResponse>builder()
              .status(HttpStatus.OK.value())
              .error(false)
              .message("Login successful!")
              .data(data)
              .build();

    } catch (Exception e) {
      return BaseResponse.<LoginResponse>builder()
              .status(HttpStatus.UNAUTHORIZED.value())
              .error(true)
//              .message("Invalid username or password.")
              .message(e.toString())
              .data(null)
              .build();
    }
  }
}
