package com.example.Healthy.App.service;
import com.example.Healthy.App.dto.DietDto;

import java.util.List;

public interface DietService {
    List<DietDto> getAllDiets();
    DietDto getDietByID(Integer id);
    DietDto createDiet(DietDto dietDto);
    DietDto updateDiet(Integer id, DietDto dietDto);
    void deleteDiet(Integer id);
}
