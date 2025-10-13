package com.example.Healthy.App.service;
import com.example.Healthy.App.dto.ActivityLogDto;
import java.util.List;

public interface ActivityLogService {
     List<ActivityLogDto> getAllActivityLogs();
     ActivityLogDto getActivityLogByID(Integer id);
     ActivityLogDto createActivityLog(ActivityLogDto activityLogDto);
     ActivityLogDto updateActivityLog(Integer id, ActivityLogDto activityLogDto);
     void deleteActivityLog(Integer id);
}
