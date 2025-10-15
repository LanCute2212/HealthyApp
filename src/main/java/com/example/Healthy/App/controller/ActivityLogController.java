package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.ActivityLogDto;
import com.example.Healthy.App.service.ActivityLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }


    @PostMapping
    public ResponseEntity<ActivityLogDto> createActivityLog(@RequestBody ActivityLogDto activityLogDto) {
        ActivityLogDto createdActivityLog = activityLogService.createActivityLog(activityLogDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActivityLog);
    }


    @GetMapping
    public ResponseEntity<List<ActivityLogDto>> getAllActivityLogs() {
        List<ActivityLogDto> activityLogs = activityLogService.getAllActivityLogs();
        return ResponseEntity.ok(activityLogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityLogDto> getActivityLogById(@PathVariable Integer id) {
        ActivityLogDto activityLog = activityLogService.getActivityLogByID(id);
        return ResponseEntity.ok(activityLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityLogDto> updateActivityLog(@PathVariable Integer id, @RequestBody ActivityLogDto activityLogDto) {
        ActivityLogDto updatedActivityLog = activityLogService.updateActivityLog(id, activityLogDto);
        return ResponseEntity.ok(updatedActivityLog);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivityLog(@PathVariable Integer id) {
        activityLogService.deleteActivityLog(id);
        return ResponseEntity.ok("Deleted activity log with ID: " + id);
    }
}