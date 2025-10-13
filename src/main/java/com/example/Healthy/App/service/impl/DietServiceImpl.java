package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.DietDto;
import com.example.Healthy.App.mapper.DietMapper;
import com.example.Healthy.App.model.Blog;
import com.example.Healthy.App.model.Diet;
import com.example.Healthy.App.repository.DietRepository;
import com.example.Healthy.App.service.DietService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DietServiceImpl implements DietService {
    private final DietMapper dietMapper;
    private final DietRepository dietRepository;
    @Override
    public List<DietDto> getAllDiets() {
        return dietRepository.findAll()
                .stream()
                .map(dietMapper::toDto)
                .toList();
    }

    @Override
    public DietDto getDietByID(Integer id) {
        return dietRepository.findById(id)
                .map(dietMapper::toDto)
                .orElseThrow(()->new RuntimeException("Diet not found"));
    }

    @Override
    public DietDto createDiet(DietDto dietDto) {
        Diet diet  = dietMapper.toEntity(dietDto);
        Diet savedDiet = dietRepository.save(diet);
        return dietMapper.toDto(savedDiet);
    }

    @Override
    public DietDto updateDiet(Integer id, DietDto dietDto) {
        Diet diet = dietRepository.findById(id).orElseThrow(()->new RuntimeException("Diet not found"));
        dietMapper.updateDietFromDto(dietDto, diet);
        Diet updatedDiet = dietRepository.save(diet);
        return dietMapper.toDto(updatedDiet);
    }

    @Override
    public void deleteDiet(Integer id) {
        dietRepository.deleteById(id);
    }
}
