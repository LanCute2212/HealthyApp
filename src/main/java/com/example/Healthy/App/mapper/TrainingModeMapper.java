package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.TrainingModeDto;
import com.example.Healthy.App.model.TrainingMode;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrainingModeMapper {
    TrainingModeDto toDto(TrainingMode trainingMode);
    TrainingMode toEntity(TrainingModeDto dto);
    void updateTrainingModeFromDto(TrainingModeDto dto, @MappingTarget TrainingMode entity);
}