package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.ActivityDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.CreateActivityRequest;
import com.example.Healthy.App.mapper.ActivityMapper;
import com.example.Healthy.App.model.Activity;
import com.example.Healthy.App.repository.ActivityRepository;
import com.example.Healthy.App.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    @Override
    public List<ActivityDto> getAllActivities(UserDto currentUserDto) {
        List<Activity> allActivities = activityRepository.findAll();
        return allActivities.stream()
                .map(activityEntity -> {
                    ActivityDto dto = activityMapper.toDto(activityEntity);
                    double caloriesBurned = calculateCaloriesBurned(activityEntity.getId(), currentUserDto, 30.0);
                    dto.setCaloriesBurnedPer30Minutes(caloriesBurned);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public double calculateCaloriesBurned(Integer activityId, UserDto currentUserDto, double durationInMinutes) {
        Activity activityEntity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + activityId));

        Double metValue = activityEntity.getMetValue();
        Double userWeightKg = currentUserDto.getWeight();
        if (metValue == null || userWeightKg == null || metValue <= 0 || userWeightKg <= 0 || durationInMinutes <= 0) {
            return 0.0;
        }

        double durationInHours = durationInMinutes / 60.0;
        return metValue * userWeightKg * durationInHours;
    }

    @Override
    public ActivityDto getActivityByID(Integer id) {
        return activityRepository.findById(id)
                .map(activityMapper::toDto)
                .orElseThrow(()->new RuntimeException("Activity not found with id: " + id));
    }

    @Override
    public ActivityDto createActivity(CreateActivityRequest createActivityRequest) {
        Activity activity = new Activity();
        activity.setName(createActivityRequest.getName());
        activity.setMetValue(createActivityRequest.getMetValue());
        Activity savedActivity = activityRepository.save(activity);
        return activityMapper.toDto(savedActivity);
    }

    @Override
    public ActivityDto updateActivity(Integer id, ActivityDto activityDto) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Activity not found with id: " + id));
        activityMapper.updateActivityFromDto(activityDto, activity);
        Activity updatedActivity = activityRepository.save(activity);
        return activityMapper.toDto(updatedActivity);
    }

    @Override
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }
    }

