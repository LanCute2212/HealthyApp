package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.ProfileForm;
import com.example.Healthy.App.dto.request.UpdateProfileForm;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    return null;
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
    return ResponseEntity.ok(userService.getUserByID(id));
  }

  @PutMapping()
  public ResponseEntity<String> updateUser(@RequestParam(name = "email") String email,
      @RequestBody UserDto form) {
    userService.updateUser(email, form);
    return ResponseEntity.ok("Update thành công");
  }

  @PutMapping("/updateProfile")
  public BaseResponse<String> updateProfile(@RequestParam String email,
      @RequestBody UpdateProfileForm form) {
    userService.updateProfile(email, form);

    return BaseResponse.<String>builder()
        .status(200)
        .error(false)
        .message("Update profile thành công")
        .build();
  }

  @GetMapping("/getInfor")
  public ResponseEntity<ProfileForm> getInforByEmail(@RequestParam String email) {
    return ResponseEntity.ok(userService.getInforByEmail(email));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
    return ResponseEntity.ok("Deleted user with ID: " + id);
  }
}
