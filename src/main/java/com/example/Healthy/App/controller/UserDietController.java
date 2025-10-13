package com.example.Healthy.App.controller;
import com.example.Healthy.App.dto.UserDietDto;
import com.example.Healthy.App.service.UserDietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userdiet")
public class UserDietController {
    private final UserDietService userDietService;
    public UserDietController(UserDietService userDietService){
        this.userDietService= userDietService;
    }

    @PostMapping
    public ResponseEntity<UserDietDto> createUserDiet(@RequestBody UserDietDto userDietDto){
        UserDietDto createdUserDiet = userDietService.createUserDiet(userDietDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDiet);
    }
    @GetMapping
    public ResponseEntity<List<UserDietDto>> getAllUserDiets(){
        return ResponseEntity.ok(userDietService.getAllUserDiets());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDietDto> getUserDietById(@PathVariable Integer id){
        return ResponseEntity.ok(userDietService.getUserDietByUserID(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDietDto> updateUserDiet(@PathVariable Integer id, @RequestBody UserDietDto userDietDto){
        UserDietDto updatedUserDiet = userDietService.updateUserDiet(id, userDietDto);
        return ResponseEntity.ok(updatedUserDiet);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserDiet(@PathVariable Integer id){
        return  ResponseEntity.ok("Deleted user diet with ID: " + id);
    }
}
