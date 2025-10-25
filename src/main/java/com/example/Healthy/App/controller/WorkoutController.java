package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.WorkoutDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.WorkoutService;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto){
        WorkoutDto createdWorkout = workoutService.createWorkout(workoutDto);
        return BaseResponse.<WorkoutDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Workout created")
                .data(createdWorkout)
                .build();
    }
    @GetMapping
    public BaseResponse<List<WorkoutDto>> getAllWorkouts(){
        List<WorkoutDto> workouts = workoutService.getAllWorkouts();
        return BaseResponse.<List<WorkoutDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(workouts)
                .build();
    }
    @GetMapping("/{id}")
    public BaseResponse<WorkoutDto> getWorkoutById(@PathVariable Integer id){
        WorkoutDto workout = workoutService.getWorkoutById(id);
        return BaseResponse.<WorkoutDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(workout)
                .build();
    }
    @PutMapping("/{id}")
    public BaseResponse<WorkoutDto> updateWorkout(@PathVariable Integer id, @RequestBody WorkoutDto workoutDto){
        WorkoutDto updatedWorkout = workoutService.updateWorkout(id, workoutDto);
        return BaseResponse.<WorkoutDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updatedWorkout)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseResponse deleteWorkout(@PathVariable Integer id){
        workoutService.deleteWorkout(id);
        return BaseResponse.builder()
                .status(Status.DELETED.getStatus())
                .error(false)
                .message(Status.DELETED.getMessage())
                .build();
    }
}