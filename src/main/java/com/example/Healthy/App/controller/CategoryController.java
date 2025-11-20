package com.example.Healthy.App.controller;
import com.example.Healthy.App.dto.CategoryDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryDto>>> getAllCategories() {
        return ResponseEntity.ok(BaseResponse.<List<CategoryDto>>builder()
                .status(200)
                .error(false)
                .data(categoryService.getAllCategories())
                .build());
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CategoryDto>> createCategory(@RequestBody CategoryDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.<CategoryDto>builder()
                .status(201)
                .error(false)
                .data(categoryService.createCategory(request))
                .build());
    }
}