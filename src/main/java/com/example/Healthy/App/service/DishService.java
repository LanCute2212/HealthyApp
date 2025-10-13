package com.example.Healthy.App.service;
import com.example.Healthy.App.dto.DishDto;
import java.util.List;

public interface DishService {
    List<DishDto> getAllDishes();
    DishDto getDishByID(Integer id);
    DishDto createDish(DishDto dishDto);
    DishDto updateDish(Integer id, DishDto dishDto);
    void deleteDish(Integer id);
}
