package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.ActivityDto;
import com.example.Healthy.App.dto.ActivityLogDto;
import com.example.Healthy.App.mapper.ActivityLogMapper;
import com.example.Healthy.App.mapper.ActivityMapper;
import com.example.Healthy.App.model.ActivityLog;
import com.example.Healthy.App.repository.ActivityLogRepository;
import com.example.Healthy.App.repository.ActivityRepository;
import com.example.Healthy.App.repository.UserRepository;
import com.example.Healthy.App.service.ActivityLogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogRepository activityLogRepository;
    private final ActivityLogMapper activityLogMapper;
    private final UserRepository userRepository;
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
    public ActivityLogDto createActivityLog(ActivityLogDto activityLogDto) {
        ActivityLog activityLog = activityLogMapper.toEntity(activityLogDto);
        ActivityLog savedActivityLog = activityLogRepository.save(activityLog);
        return activityLogMapper.toDto(savedActivityLog);
    }

    @Override
    public ActivityLogDto updateActivityLog(Integer id, ActivityLogDto activityLogDto) {
        ActivityLog activityLog = activityLogRepository.findById(id).orElseThrow(()-> new RuntimeException("ActivityLog not found"));
        activityLogMapper.updateActivityLogFromDto(activityLogDto, activityLog);
        ActivityLog updatedActivityLog = activityLogRepository.save(activityLog);
        return activityLogMapper.toDto(updatedActivityLog);
    }

    @Override
    public void deleteActivityLog(Integer id) {
        activityLogRepository.deleteById(id);
    }
}
