package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.UserDietDto;
import com.example.Healthy.App.model.UserDiet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserDietMapper {
    UserDietDto toDto(UserDiet userDiet);
    UserDiet toEntity(UserDietDto dto);
    void updateUserDietFromDto(UserDietDto userDietDto, @MappingTarget UserDiet entity);
}