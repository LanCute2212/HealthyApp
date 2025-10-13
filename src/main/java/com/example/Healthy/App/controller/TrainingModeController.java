package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.TrainingModeDto;
import com.example.Healthy.App.service.TrainingModeService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Training")
public class TrainingModeController {
    private final TrainingModeService trainingModeService;

    public TrainingModeController(TrainingModeService trainingModeService){
        this.trainingModeService = trainingModeService;
    }
    @PostMapping
    public ResponseEntity<TrainingModeDto> createTrainingMode(@RequestBody TrainingModeDto trainingModeDto){
        TrainingModeDto createdMode = trainingModeService.createMode(trainingModeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMode);
    }
    @GetMapping
    public ResponseEntity<List<TrainingModeDto>> getAllModes(){
        return ResponseEntity.ok(trainingModeService.getAllModes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TrainingModeDto> getModeById(@PathVariable Integer id){
        return  ResponseEntity.ok(trainingModeService.getModeByID(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TrainingModeDto> updateMode(@PathVariable Integer id, @RequestBody TrainingModeDto trainingModeDto){
        TrainingModeDto updatedMode = trainingModeService.updateMode(id, trainingModeDto);
        return ResponseEntity.ok(updatedMode);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMode(@PathVariable Integer id){
        return ResponseEntity.ok("Deleted mode with id: "+ id);
    }
}
