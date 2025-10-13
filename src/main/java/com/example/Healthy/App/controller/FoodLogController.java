package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.FoodLogDto;
import com.example.Healthy.App.service.FoodLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<FoodLogDto> createFoodLog(@RequestBody FoodLogDto foodLogDto) {
        FoodLogDto createdFoodLog = foodLogService.createFoodLog(foodLogDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFoodLog);
    }

    @GetMapping
    public ResponseEntity<List<FoodLogDto>> getAllFoodLogs() {
        return ResponseEntity.ok(foodLogService.getAllFoodLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodLogDto> getFoodLogById(@PathVariable Integer id) {
        return ResponseEntity.ok(foodLogService.getFoodLogByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodLogDto> updateFoodLog(@PathVariable Integer id, @RequestBody FoodLogDto foodLogDto) {
        FoodLogDto updatedFoodLog = foodLogService.updateFoodLog(id, foodLogDto);
        return ResponseEntity.ok(updatedFoodLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodLog(@PathVariable Integer id) {
        foodLogService.deleteFoodLog(id);
        return ResponseEntity.ok("Deleted food log with ID: " + id);
    }
}
