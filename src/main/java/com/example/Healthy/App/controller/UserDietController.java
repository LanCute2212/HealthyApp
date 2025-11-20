package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.UserDietDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.UserDietService;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<UserDietDto> createUserDiet(@RequestBody UserDietDto userDietDto){
        UserDietDto createdUserDiet = userDietService.createUserDiet(userDietDto);
        return BaseResponse.<UserDietDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("User diet created")
                .data(createdUserDiet)
                .build();
    }
    @GetMapping
    public BaseResponse<List<UserDietDto>> getAllUserDiets(){
        List<UserDietDto> userDiets = userDietService.getAllUserDiets();
        return BaseResponse.<List<UserDietDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(userDiets)
                .build();
    }
    @GetMapping("/{id}")
    public BaseResponse<UserDietDto> getUserDietById(@PathVariable Integer id){
        UserDietDto userDiet = userDietService.getUserDietByUserID(id);
        return BaseResponse.<UserDietDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(userDiet)
                .build();
    }
    @PutMapping("/{id}")
    public BaseResponse<UserDietDto> updateUserDiet(@PathVariable Integer id, @RequestBody UserDietDto userDietDto){
        UserDietDto updatedUserDiet = userDietService.updateUserDiet(id, userDietDto);
        return BaseResponse.<UserDietDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updatedUserDiet)
                .build();
    }
}