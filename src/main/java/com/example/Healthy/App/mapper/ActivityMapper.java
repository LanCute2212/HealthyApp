package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.ActivityDto;
import com.example.Healthy.App.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    ActivityDto toDto(Activity activity);
    Activity toEntity(ActivityDto dto);
    void updateActivityFromDto(ActivityDto dto, @MappingTarget Activity entity);
}