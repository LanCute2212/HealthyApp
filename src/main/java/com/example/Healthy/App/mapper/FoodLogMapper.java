package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.FoodLogDto;
import com.example.Healthy.App.model.FoodLog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FoodLogMapper {
    FoodLogDto toDto(FoodLog foodLog);
    FoodLog toEntity(FoodLogDto dto);
    void updateFoodLogFromDto(FoodLogDto dto, @MappingTarget FoodLog entity);
}