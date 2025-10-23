package com.example.Healthy.App.service;

import com.example.Healthy.App.dto.ActivityDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.CreateActivityRequest;

import java.util.List;

public interface ActivityService {
    List<ActivityDto> getAllActivities(UserDto currentUserDto);
    ActivityDto getActivityByID(Integer id);
    ActivityDto createActivity(CreateActivityRequest createActivityRequest);
    ActivityDto updateActivity(Integer id, ActivityDto activityDto);
    void deleteActivity(Integer id);
    double calculateCaloriesBurned(Integer activityId, UserDto userDto, double durationInMinutes);
}
