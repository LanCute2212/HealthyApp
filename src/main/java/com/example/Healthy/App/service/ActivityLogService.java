package com.example.Healthy.App.service;
import com.example.Healthy.App.dto.ActivityLogDto;
import com.example.Healthy.App.dto.request.LogActivityRequest;
import com.example.Healthy.App.dto.request.UpdateActivityLogRequest;

import java.util.List;

public interface ActivityLogService {
     List<ActivityLogDto> getAllActivityLogs();
     ActivityLogDto getActivityLogByID(Integer id);
     ActivityLogDto createActivityLog(LogActivityRequest requestDto);
     ActivityLogDto updateActivityLog(Integer id, UpdateActivityLogRequest updateDto);
     void deleteActivityLog(Integer id);
}
