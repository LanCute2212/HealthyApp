package com.example.Healthy.App.service;

import com.example.Healthy.App.dto.ActivityDto;
import java.util.List;

public interface ActivityService {
    List<ActivityDto> getAllActivities();
    ActivityDto getActivityByID(Integer id);
    ActivityDto createActivity(ActivityDto activityDto);
    ActivityDto updateActivity(Integer id, ActivityDto activityDto);
    void deleteActivity(Integer id);
}
