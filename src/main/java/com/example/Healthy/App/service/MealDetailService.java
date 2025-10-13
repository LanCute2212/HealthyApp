package com.example.Healthy.App.service;
import com.example.Healthy.App.dto.MealDetailDto;
import java.util.List;

public interface MealDetailService {
    List<MealDetailDto> getAllMealDetails();
    MealDetailDto getMealDetailByID(Integer id);
    MealDetailDto createMealDetail(MealDetailDto mealDetailDto);
    MealDetailDto updateMealDetail(Integer id, MealDetailDto mealDetailDto);
    void deleteMealDetail(Integer id);
}
