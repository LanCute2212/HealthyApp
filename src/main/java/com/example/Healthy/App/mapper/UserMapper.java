package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto dto);
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);
}