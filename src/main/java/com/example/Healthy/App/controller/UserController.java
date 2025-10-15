package com.example.Healthy.App.controller;
import com.example.Healthy.App.dto.UserDietDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.ProfileForm;
import com.example.Healthy.App.dto.request.UpdateProfileForm;
import com.example.Healthy.App.service.UserDietService;
import com.example.Healthy.App.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService= userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return null;
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserByID(id));
    }
    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestParam(name = "email") String email, @RequestBody UserDto form){
        userService.updateUser(email, form);
        return ResponseEntity.ok("Update thành công");
    }
    @GetMapping("/getInfor")
    public  ResponseEntity<ProfileForm> getInforByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.getInforByEmail(email));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        return  ResponseEntity.ok("Deleted user with ID: " + id);
    }
}
