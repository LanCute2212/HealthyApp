package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.MealDetailDto;
import com.example.Healthy.App.model.MealDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MealDetailMapper {
    MealDetailDto toDto(MealDetail mealDetail);
    MealDetail toEntity(MealDetailDto dto);
    void updateMealDetailFromDto(MealDetailDto dto, @MappingTarget MealDetail entity);
}