package com.example.Healthy.App.service;
import com.example.Healthy.App.dto.TrainingModeDto;
import java.util.List;

public interface TrainingModeService {
    List<TrainingModeDto> getAllModes();
    TrainingModeDto getModeByID(Integer id);
    TrainingModeDto createMode(TrainingModeDto trainingModeDto);
    TrainingModeDto updateMode(Integer id,TrainingModeDto trainingModeDto);
    void deleteMode(Integer id);
}
