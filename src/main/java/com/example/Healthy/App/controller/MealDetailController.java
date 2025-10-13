package com.example.Healthy.App.controller;
import com.example.Healthy.App.dto.MealDetailDto;
import com.example.Healthy.App.service.MealDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MealDetailDto> createMealDetail(@RequestBody MealDetailDto mealDetailDto) {
        MealDetailDto createdMealDetail = mealDetailService.createMealDetail(mealDetailDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMealDetail);
    }

    @GetMapping
    public ResponseEntity<List<MealDetailDto>> getAllMealDetails() {
        return ResponseEntity.ok(mealDetailService.getAllMealDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealDetailDto> getMealDetailById(@PathVariable Integer id) {
        return ResponseEntity.ok(mealDetailService.getMealDetailByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealDetailDto> updateMealDetail(@PathVariable Integer id, @RequestBody MealDetailDto mealDetailDto) {
        MealDetailDto updatedMealDetail = mealDetailService.updateMealDetail(id, mealDetailDto);
        return ResponseEntity.ok(updatedMealDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMealDetail(@PathVariable Integer id) {
        mealDetailService.deleteMealDetail(id);
        return ResponseEntity.ok("Deleted meal detail with ID: " + id);
    }
}