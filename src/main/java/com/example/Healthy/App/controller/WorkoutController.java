package com.example.Healthy.App.controller;
import com.example.Healthy.App.dto.WorkoutDto;
import com.example.Healthy.App.service.WorkoutService;
import org.hibernate.Internal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }
    @PostMapping
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto){
        WorkoutDto createdWorkout = workoutService.createWorkout(workoutDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }
    @GetMapping
    public ResponseEntity<List<WorkoutDto>> getAllWorkouts(){
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDto> getWorkoutById(@PathVariable Integer id){
        return ResponseEntity.ok(workoutService.getWorkoutById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutDto> updateWorkout(@PathVariable Integer id, @RequestBody WorkoutDto workoutDto){
        WorkoutDto updatedWorkout = workoutService.updateWorkout(id, workoutDto);
        return ResponseEntity.ok(updatedWorkout);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable Integer id){
        workoutService.deleteWorkout(id);
        return ResponseEntity.ok("Deleted workout with ID: "+id);
    }

}
