package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.DishDto;
import com.example.Healthy.App.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DishMapper {
    DishDto toDto(Dish dish);
    Dish toEntity(DishDto dto);
    void updateDishFromDto(DishDto dto, @MappingTarget Dish entity);
}