package com.example.Healthy.App.service;
import com.example.Healthy.App.dto.WorkoutDto;
import java.util.List;

public interface WorkoutService {
    List<WorkoutDto> getAllWorkouts();
    WorkoutDto getWorkoutById(Integer id);
    WorkoutDto createWorkout(WorkoutDto workoutDto);
    WorkoutDto updateWorkout(Integer id, WorkoutDto workoutDto);
    void deleteWorkout(Integer id);
}
