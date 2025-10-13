package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.DietDto;
import com.example.Healthy.App.service.DietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diet")
public class DietController {
    private final DietService DietService;

    public DietController(DietService DietService){
        this.DietService= DietService;
    }

    @PostMapping
    public ResponseEntity<DietDto> createDiet(@RequestBody DietDto DietDto){
        DietDto createdDiet = DietService.createDiet(DietDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiet);
    }
    @GetMapping
    public ResponseEntity<List<DietDto>> getAllDiets(){
        return ResponseEntity.ok(DietService.getAllDiets());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DietDto> getDietById(@PathVariable Integer id){
        return ResponseEntity.ok(DietService.getDietByID(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DietDto> updateDiet(@PathVariable Integer id, @RequestBody DietDto DietDto){
        DietDto updatedDiet = DietService.updateDiet(id, DietDto);
        return ResponseEntity.ok(updatedDiet);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiet(@PathVariable Integer id){
        return  ResponseEntity.ok("Deleted diet with ID: " + id);
    }
}
