package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.FoodLogDto;
import com.example.Healthy.App.mapper.FoodLogMapper;
import com.example.Healthy.App.model.FoodLog;
import com.example.Healthy.App.repository.FoodLogRepository;
import com.example.Healthy.App.service.FoodLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FoodLogServiceImpl implements FoodLogService {
    private final FoodLogMapper foodLogMapper;
    private final FoodLogRepository foodLogRepository;
    @Override
    public List<FoodLogDto> getAllFoodLogs() {
        return foodLogRepository.findAll()
                .stream()
                .map(foodLogMapper::toDto)
                .toList();
    }

    @Override
    public FoodLogDto getFoodLogByID(Integer id) {
        return foodLogRepository.findById(id)
                .map(foodLogMapper::toDto)
                .orElseThrow(()->new RuntimeException("FoodLog not found"));
    }

    @Override
    public FoodLogDto createFoodLog(FoodLogDto foodLogDto) {
        FoodLog foodLog = foodLogMapper.toEntity(foodLogDto);
        FoodLog savedFoodLog = foodLogRepository.save(foodLog);
        return foodLogMapper.toDto(savedFoodLog);
    }

    @Override
    public FoodLogDto updateFoodLog(Integer id, FoodLogDto foodLogDto) {
        FoodLog foodLog = foodLogRepository.findById(id).orElseThrow(()->new RuntimeException("Food Log not found"));
        foodLogMapper.updateFoodLogFromDto(foodLogDto,foodLog);
        FoodLog updatedFoodLog = foodLogRepository.save(foodLog);
        return foodLogMapper.toDto(updatedFoodLog);
    }

    @Override
    public void deleteFoodLog(Integer id) {
        foodLogRepository.deleteById(id);
    }
}
