package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.ActivityLogDto;
import com.example.Healthy.App.model.ActivityLog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ActivityLogMapper {
    ActivityLogDto toDto(ActivityLog activityLog);
    ActivityLog toEntity(ActivityLogDto dto);
    void updateActivityLogFromDto(ActivityLogDto dto, @MappingTarget ActivityLog entity);
}