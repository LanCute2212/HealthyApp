package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.MealDetailDto;
import com.example.Healthy.App.mapper.MealDetailMapper;
import com.example.Healthy.App.model.MealDetail;
import com.example.Healthy.App.repository.MealDetailRepository;
import com.example.Healthy.App.service.MealDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MealDetailServiceImpl implements MealDetailService {
    private final MealDetailRepository mealDetailRepository;
    private final MealDetailMapper mealDetailMapper;
    @Override
    public List<MealDetailDto> getAllMealDetails() {
        return mealDetailRepository.findAll()
                .stream()
                .map(mealDetailMapper::toDto)
                .toList();
    }

    @Override
    public MealDetailDto getMealDetailByID(Integer id){
        return mealDetailRepository.findById(id)
                .map(mealDetailMapper::toDto).orElseThrow(()->new RuntimeException("MealDetail not found"));
    }

    @Override
    public MealDetailDto createMealDetail(MealDetailDto mealDetailDto) {
        MealDetail mealDetail = mealDetailMapper.toEntity(mealDetailDto);
        MealDetail savedMealDetail = mealDetailRepository.save(mealDetail);
        return mealDetailMapper.toDto(savedMealDetail);
    }

    @Override
    public MealDetailDto updateMealDetail(Integer id, MealDetailDto mealDetailDto) {
        MealDetail mealDetail = mealDetailRepository.findById(id).orElseThrow(()->new RuntimeException("Meal Detail not found"));
        mealDetailMapper.updateMealDetailFromDto(mealDetailDto, mealDetail);
        MealDetail updatedMealDetail = mealDetailRepository.save(mealDetail);
        return mealDetailMapper.toDto(updatedMealDetail);
    }

    @Override
    public void deleteMealDetail(Integer id) {
        mealDetailRepository.deleteById(id);
    }
}
