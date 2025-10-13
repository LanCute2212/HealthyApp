package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.DietDto;
import com.example.Healthy.App.model.Diet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DietMapper {
    DietDto toDto(Diet diet);
    Diet toEntity(DietDto dto);
    void updateDietFromDto(DietDto dto, @MappingTarget Diet entity);
}