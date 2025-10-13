package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.ActivityDto;
import com.example.Healthy.App.mapper.ActivityMapper;
import com.example.Healthy.App.model.Activity;
import com.example.Healthy.App.repository.ActivityRepository;
import com.example.Healthy.App.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    @Override
    public List<ActivityDto> getAllActivities() {
        return activityRepository.findAll()
                .stream()
                .map(activityMapper::toDto)
                .toList();
    }
    @Override
    public ActivityDto getActivityByID(Integer id) {
        return activityRepository.findById(id)
                .map(activityMapper::toDto)
                .orElseThrow(()->new RuntimeException("Activity not found"));
    }

    @Override
    public ActivityDto createActivity(ActivityDto activityDto) {
        Activity activity = activityMapper.toEntity(activityDto);
        Activity savedActivity = activityRepository.save(activity);
        return activityMapper.toDto(savedActivity);
    }

    @Override
    public ActivityDto updateActivity(Integer id, ActivityDto activityDto) {
        Activity activity = activityRepository.findById(id).orElseThrow(()->new RuntimeException("Activity not found"));
        activityMapper.updateActivityFromDto(activityDto, activity);
        Activity updatedActivity = activityRepository.save(activity);
        return activityMapper.toDto(updatedActivity);
    }

    @Override
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }
}
