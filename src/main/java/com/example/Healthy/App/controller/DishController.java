package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.DishDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public BaseResponse<DishDto> createDish(@RequestBody DishDto dishDto) {
        DishDto createdDish = dishService.createDish(dishDto);
        return BaseResponse.<DishDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Dish created")
                .data(createdDish)
                .build();
    }

    @GetMapping
    public BaseResponse<List<DishDto>> getAllDishes() {
        List<DishDto> dishes = dishService.getAllDishes();
        return BaseResponse.<List<DishDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(dishes)
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<DishDto> getDishById(@PathVariable Integer id) {
        DishDto dish = dishService.getDishByID(id);
        return BaseResponse.<DishDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(dish)
                .build();
    }

    @PutMapping("/{id}")
    public BaseResponse<DishDto> updateDish(@PathVariable Integer id, @RequestBody DishDto dishDto) {
        DishDto updatedDish = dishService.updateDish(id, dishDto);
        return BaseResponse.<DishDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updatedDish)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteDish(@PathVariable Integer id) {
        dishService.deleteDish(id);
        return BaseResponse.builder()
                .status(Status.DELETED.getStatus())
                .error(false)
                .message(Status.DELETED.getMessage())
                .build();
    }
}