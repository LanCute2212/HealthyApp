package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.CategoryDto;
import com.example.Healthy.App.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public Category toEntity(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}