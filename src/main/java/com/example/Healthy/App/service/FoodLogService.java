package com.example.Healthy.App.service;

import com.example.Healthy.App.dto.FoodLogDto;

import java.util.List;

public interface FoodLogService {
    List<FoodLogDto> getAllFoodLogs();
    FoodLogDto getFoodLogByID(Integer id);
    FoodLogDto createFoodLog(FoodLogDto foodLogDto);
    FoodLogDto updateFoodLog(Integer id, FoodLogDto foodLogDto);
    void deleteFoodLog(Integer id);
}
