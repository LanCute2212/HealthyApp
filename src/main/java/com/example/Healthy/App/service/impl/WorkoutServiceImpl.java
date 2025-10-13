package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.WorkoutDto;
import com.example.Healthy.App.mapper.WorkoutMapper;
import com.example.Healthy.App.model.Workout;
import com.example.Healthy.App.repository.WorkoutRepository;
import com.example.Healthy.App.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    @Override
    public List<WorkoutDto> getAllWorkouts() {
        return workoutRepository.findAll()
                .stream().map(workoutMapper::toDto).toList();
    }

    @Override
    public WorkoutDto getWorkoutById(Integer id) {
        return workoutRepository.findById(id).map(workoutMapper::toDto).orElseThrow(()->new RuntimeException("Workout not found"));
    }

    @Override
    public WorkoutDto createWorkout(WorkoutDto workoutDto) {
        Workout workout = workoutMapper.toEntity(workoutDto);
        Workout savedWorkout = workoutRepository.save(workout);
        return workoutMapper.toDto(savedWorkout);
    }

    @Override
    public WorkoutDto updateWorkout(Integer id, WorkoutDto workoutDto) {
        Workout workout = workoutRepository.findById(id).orElseThrow(()->new RuntimeException("Workout not found"));
        workoutMapper.updateWorkoutFromDto(workoutDto, workout);
        Workout updatedWorkout = workoutRepository.save(workout);
        return workoutMapper.toDto(updatedWorkout);
    }

    @Override
    public void deleteWorkout(Integer id) {
        workoutRepository.deleteById(id);
    }
}
