package com.example.Healthy.App.service;

import com.example.Healthy.App.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
        List<CategoryDto> getAllCategories();
        CategoryDto createCategory(CategoryDto categoryDto);
    }
