package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.ActivityDto;
import com.example.Healthy.App.dto.ActivityLogDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.LogActivityRequest;
import com.example.Healthy.App.dto.request.UpdateActivityLogRequest;
import com.example.Healthy.App.mapper.ActivityLogMapper;
import com.example.Healthy.App.mapper.ActivityMapper;
import com.example.Healthy.App.mapper.UserMapper;
import com.example.Healthy.App.model.Activity;
import com.example.Healthy.App.model.ActivityLog;
import com.example.Healthy.App.model.User;
import com.example.Healthy.App.repository.ActivityLogRepository;
import com.example.Healthy.App.repository.ActivityRepository;
import com.example.Healthy.App.repository.UserRepository;
import com.example.Healthy.App.service.ActivityLogService;
import com.example.Healthy.App.service.ActivityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogRepository activityLogRepository;
    private final ActivityLogMapper activityLogMapper;
    private final UserRepository userRepository;
    private final ActivityService activityService;
    private final UserMapper userMapper;
    private final ActivityRepository activityRepository;

    @Override
    @Transactional
    public List<ActivityLogDto> getAllActivityLogs() {
        return activityLogRepository.findAll()
                .stream()
                .map(activityLogMapper::toDto)
                .toList();
    }

    @Override
    public ActivityLogDto getActivityLogByID(Integer id) {
        return activityLogRepository.findById(id)
                .map(activityLogMapper::toDto)
                .orElseThrow(() ->new RuntimeException("ActivityLog not Found"));
    }

    @Override
    public ActivityLogDto createActivityLog(@RequestBody LogActivityRequest requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Activity activity = activityRepository.findById(requestDto.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        UserDto userDto = userMapper.toDto(user);

        double caloriesBurned = activityService.calculateCaloriesBurned(activity.getId(), userDto, requestDto.getDurationInMinutes());

        ActivityLog newLog = new ActivityLog();
        newLog.setUser(user);
        newLog.setActivity(activity);
        newLog.setDurationMinutes(requestDto.getDurationInMinutes());
        newLog.setCaloriesBurned(caloriesBurned);
        newLog.setLogDate(LocalDateTime.now());

        ActivityLog savedActivityLog = activityLogRepository.save(newLog);
        return activityLogMapper.toDto(savedActivityLog);
    }

    @Override
    public ActivityLogDto updateActivityLog(Integer id, UpdateActivityLogRequest updateDto) {
        ActivityLog existingLog = activityLogRepository.findById(id).orElseThrow(()-> new RuntimeException("ActivityLog not found"));
        User user = existingLog.getUser();
        Activity activity = existingLog.getActivity();

        double newCaloriesBurned = activityService.calculateCaloriesBurned(
                activity.getId(),
                userMapper.toDto(user),
                updateDto.getDurationInMinutes()
        );

        existingLog.setCaloriesBurned(updateDto.getDurationInMinutes());
        existingLog.setCaloriesBurned(newCaloriesBurned);

        ActivityLog updatedActivityLog = activityLogRepository.save(existingLog);
        return activityLogMapper.toDto(updatedActivityLog);
    }

    @Override
    public void deleteActivityLog(Integer id) {
        activityLogRepository.deleteById(id);
    }
}
