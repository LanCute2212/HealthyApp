package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.MealDetailDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.MealDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mealdetails")
public class MealDetailController {

    private final MealDetailService mealDetailService;

    public MealDetailController(MealDetailService mealDetailService) {
        this.mealDetailService = mealDetailService;
    }

    @PostMapping
    public BaseResponse<MealDetailDto> createMealDetail(@RequestBody MealDetailDto mealDetailDto) {
        MealDetailDto createdMealDetail = mealDetailService.createMealDetail(mealDetailDto);
        return BaseResponse.<MealDetailDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Meal detail created")
                .data(createdMealDetail)
                .build();
    }

    @GetMapping
    public BaseResponse<List<MealDetailDto>> getAllMealDetails() {
        List<MealDetailDto> mealDetails = mealDetailService.getAllMealDetails();
        return BaseResponse.<List<MealDetailDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(mealDetails)
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<MealDetailDto> getMealDetailById(@PathVariable Integer id) {
        MealDetailDto mealDetail = mealDetailService.getMealDetailByID(id);
        return BaseResponse.<MealDetailDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(mealDetail)
                .build();
    }

    @PutMapping("/{id}")
    public BaseResponse<MealDetailDto> updateMealDetail(@PathVariable Integer id, @RequestBody MealDetailDto mealDetailDto) {
        MealDetailDto updatedMealDetail = mealDetailService.updateMealDetail(id, mealDetailDto);
        return BaseResponse.<MealDetailDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updatedMealDetail)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteMealDetail(@PathVariable Integer id) {
        mealDetailService.deleteMealDetail(id);
        return BaseResponse.builder()
                .status(Status.DELETED.getStatus())
                .error(false)
                .message(Status.DELETED.getMessage())
                .build();
    }
}