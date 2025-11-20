package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.CategoryDto;
import com.example.Healthy.App.mapper.CategoryMapper;
import com.example.Healthy.App.model.Category;
import com.example.Healthy.App.repository.CategoryRepository;
import com.example.Healthy.App.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName())) {
            throw new RuntimeException("Category exists");
        }
        Category category = categoryMapper.toEntity(categoryDto);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toDto(saved);
    }
}