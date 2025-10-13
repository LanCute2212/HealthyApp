package com.example.Healthy.App.mapper;
import com.example.Healthy.App.dto.WorkoutDto;
import com.example.Healthy.App.model.Workout;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    WorkoutDto toDto(Workout workout);
    Workout toEntity(WorkoutDto dto);
    void updateWorkoutFromDto(WorkoutDto workoutDto, @MappingTarget Workout entity);
}