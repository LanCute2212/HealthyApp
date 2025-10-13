package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.TrainingModeDto;
import com.example.Healthy.App.mapper.TrainingModeMapper;
import com.example.Healthy.App.model.TrainingMode;
import com.example.Healthy.App.repository.TrainingModeRepository;
import com.example.Healthy.App.service.TrainingModeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TrainingModeServiceImpl implements TrainingModeService {
    private final TrainingModeRepository trainingModeRepository;
    private final TrainingModeMapper trainingModeMapper;
    @Override
    public List<TrainingModeDto> getAllModes() {
        return trainingModeRepository.findAll()
                .stream()
                .map(trainingModeMapper::toDto)
                .toList();
    }

    @Override
    public TrainingModeDto getModeByID(Integer id) {
        return trainingModeRepository.findById(id)
                .map(trainingModeMapper::toDto)
                .orElseThrow(()->new RuntimeException("Training Mode not found"));
    }

    @Override
    public TrainingModeDto createMode(TrainingModeDto trainingModeDto) {
        TrainingMode trainingMode = trainingModeMapper.toEntity(trainingModeDto);
        TrainingMode savedTrainingMode = trainingModeRepository.save(trainingMode);
        return trainingModeMapper.toDto(savedTrainingMode);
    }

    @Override
    public TrainingModeDto updateMode(Integer id, TrainingModeDto trainingModeDto) {
        TrainingMode trainingMode = trainingModeRepository.findById(id).orElseThrow(()->new RuntimeException("Training Mode not found"));
        trainingModeMapper.updateTrainingModeFromDto(trainingModeDto, trainingMode);
        TrainingMode updatedTrainingMode = trainingModeRepository.save(trainingMode);
        return trainingModeMapper.toDto(updatedTrainingMode);
    }

    @Override
    public void deleteMode(Integer id) {
        trainingModeRepository.deleteById(id);
    }
}
