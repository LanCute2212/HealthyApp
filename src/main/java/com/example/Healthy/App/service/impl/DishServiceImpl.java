package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.DishDto;
import com.example.Healthy.App.mapper.DishMapper;
import com.example.Healthy.App.model.Dish;
import com.example.Healthy.App.repository.DishRepository;
import com.example.Healthy.App.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    @Override
    public List<DishDto> getAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(dishMapper::toDto)
                .toList();
    }

    @Override
    public DishDto getDishByID(Integer id) {
        return dishRepository.findById(id)
                .map(dishMapper::toDto)
                .orElseThrow(()->new RuntimeException("Dish not found"));
    }

    @Override
    public DishDto createDish(DishDto dishDto) {
        Dish dish = dishMapper.toEntity(dishDto);
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.toDto(savedDish);
    }

    @Override
    public DishDto updateDish(Integer id, DishDto dishDto) {
        Dish dish = dishRepository.findById(id).orElseThrow(()->new RuntimeException("Dish not found"));
        dishMapper.updateDishFromDto(dishDto, dish);
        Dish updatedDish = dishRepository.save(dish);
        return dishMapper.toDto(updatedDish);
    }

    @Override
    public void deleteDish(Integer id) {
        dishRepository.deleteById(id);
    }
}
