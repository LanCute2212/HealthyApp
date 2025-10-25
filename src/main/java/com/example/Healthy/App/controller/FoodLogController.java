package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.FoodLogDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.FoodLogService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foodlogs")
public class FoodLogController {

    private final FoodLogService foodLogService;

    public FoodLogController(FoodLogService foodLogService) {
        this.foodLogService = foodLogService;
    }

    @PostMapping
    public BaseResponse<FoodLogDto> createFoodLog(@RequestBody FoodLogDto foodLogDto) {
        FoodLogDto createdFoodLog = foodLogService.createFoodLog(foodLogDto);
        return BaseResponse.<FoodLogDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Food log created")
                .data(createdFoodLog)
                .build();
    }

    @GetMapping
    public BaseResponse<List<FoodLogDto>> getAllFoodLogs() {
        List<FoodLogDto> foodLogs = foodLogService.getAllFoodLogs();
        return BaseResponse.<List<FoodLogDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(foodLogs)
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<FoodLogDto> getFoodLogById(@PathVariable Integer id) {
        FoodLogDto foodLog = foodLogService.getFoodLogByID(id);
        return BaseResponse.<FoodLogDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(foodLog)
                .build();
    }

    @PutMapping("/{id}")
    public BaseResponse<FoodLogDto> updateFoodLog(@PathVariable Integer id, @RequestBody FoodLogDto foodLogDto) {
        FoodLogDto updatedFoodLog = foodLogService.updateFoodLog(id, foodLogDto);
        return BaseResponse.<FoodLogDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updatedFoodLog)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteFoodLog(@PathVariable Integer id) {
        foodLogService.deleteFoodLog(id);
        return BaseResponse.builder()
                .status(Status.DELETED.getStatus())
                .error(false)
                .message(Status.DELETED.getMessage())
                .build();
    }
}