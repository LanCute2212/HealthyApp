package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.RegisterDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.ProfileForm;
import com.example.Healthy.App.dto.request.UpdateProfileForm;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public BaseResponse<UserDto> createUser(@RequestBody RegisterDto registerDto) {
    UserDto createdUser = userService.createUser(registerDto);
    return BaseResponse.<UserDto>builder()
            .status(HttpStatus.CREATED.value())
            .error(false)
            .message("User created")
            .data(createdUser)
            .build();
  }

  @GetMapping
  public BaseResponse<List<UserDto>> getAllUsers() {
    List<UserDto> users = userService.getAllUsers();
    return BaseResponse.<List<UserDto>>builder()
            .status(HttpStatus.OK.value())
            .error(false)
            .message(".")
            .data(users)
            .build();
  }

  @GetMapping("/{id}")
  public BaseResponse<UserDto> getUserById(@PathVariable Integer id) {
    UserDto user = userService.getUserByID(id);
    return BaseResponse.<UserDto>builder()
            .status(HttpStatus.OK.value())
            .error(false)
            .message(".")
            .data(user)
            .build();
  }

  @PutMapping()
  public BaseResponse updateUser(@RequestParam(name = "email") String email,
                                          @RequestBody UserDto form) {
    UserDto updatedUser = userService.updateUser(email, form);
    return BaseResponse.builder()
            .status(Status.UPDATED.getStatus())
            .error(false)
            .message(Status.UPDATED.getMessage())
            .data(updatedUser)
            .build();
  }

  @PostMapping("/updateProfile")
  public BaseResponse<Object> updateProfile(@RequestParam String email,
                                            @RequestBody UpdateProfileForm form) {
    userService.updateProfile(email, form);

    return BaseResponse.builder()
            .status(Status.UPDATED.getStatus())
            .error(false)
            .message(Status.UPDATED.getMessage())
            .build();
  }

  @GetMapping("/getInfor")
  public BaseResponse<ProfileForm> getInforByEmail(@RequestParam String email) {
    ProfileForm profile = userService.getInforByEmail(email);
    return BaseResponse.<ProfileForm>builder()
            .status(HttpStatus.OK.value())
            .error(false)
            .message(".")
            .data(profile)
            .build();
  }

  @DeleteMapping("/{id}")
  public BaseResponse deleteUser(@PathVariable Integer id) {
    userService.deleteUser(id);
    return BaseResponse.builder()
            .status(Status.DELETED.getStatus())
            .error(false)
            .message(Status.DELETED.getMessage())
            .build();
  }
}