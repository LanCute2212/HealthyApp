package com.example.Healthy.App.controller;
import com.example.Healthy.App.dto.DishDto;
import com.example.Healthy.App.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DishDto> createDish(@RequestBody DishDto dishDto) {
        DishDto createdDish = dishService.createDish(dishDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getDishById(@PathVariable Integer id) {
        return ResponseEntity.ok(dishService.getDishByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDto> updateDish(@PathVariable Integer id, @RequestBody DishDto dishDto) {
        DishDto updatedDish = dishService.updateDish(id, dishDto);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Integer id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok("Deleted dish with ID: " + id);
    }
}