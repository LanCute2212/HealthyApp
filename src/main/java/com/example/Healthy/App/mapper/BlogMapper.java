package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.BlogDto;
import com.example.Healthy.App.model.Blog;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    BlogDto toDto(Blog blog);
    Blog toEntity(BlogDto dto);
    void updateBlogFromDto(BlogDto dto, @MappingTarget Blog entity);
}