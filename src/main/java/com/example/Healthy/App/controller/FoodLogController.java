package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.FoodLogDto;
import com.example.Healthy.App.dto.response.BaseResponse;
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
}