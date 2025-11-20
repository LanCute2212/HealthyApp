package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.ActivityLogDto;
import com.example.Healthy.App.dto.request.LogActivityRequest;
import com.example.Healthy.App.dto.request.UpdateActivityLogRequest;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.ActivityLogService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }
    @PreAuthorize("hasAuthority('User')")
    @PostMapping
    public BaseResponse<ActivityLogDto> createActivityLog(@RequestBody LogActivityRequest requestDto) {
        ActivityLogDto createdActivityLog = activityLogService.createActivityLog(requestDto);
        return BaseResponse.<ActivityLogDto>builder()
                .status(HttpStatus.CREATED.value())
                .message("Activity log created")
                .error(false)
                .data(createdActivityLog)
                .build();
    }


    @GetMapping
    public BaseResponse<List<ActivityLogDto>> getAllActivityLogs() {
        List<ActivityLogDto> activityLogs = activityLogService.getAllActivityLogs();
        return BaseResponse.<List<ActivityLogDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(activityLogs)
                .build();
    }


    @GetMapping("/{id}")
    public BaseResponse<ActivityLogDto> getActivityLogById(@PathVariable Integer id) {
        ActivityLogDto activityLog = activityLogService.getActivityLogByID(id);
        return BaseResponse.<ActivityLogDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(activityLog)
                .build();
    }
    @PreAuthorize("hasAuthority('User')")
    @PutMapping("/{id}")
    public BaseResponse<ActivityLogDto> updateActivityLog(@PathVariable Integer id, @RequestBody UpdateActivityLogRequest updateDto) {
        ActivityLogDto updatedActivityLog = activityLogService.updateActivityLog(id, updateDto);
        return BaseResponse.<ActivityLogDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updatedActivityLog)
                .build();
    }
    @PreAuthorize("hasAuthority('User')")
    @DeleteMapping("/{id}")
    public BaseResponse deleteActivityLog(@PathVariable Integer id) {
        activityLogService.deleteActivityLog(id);
        return BaseResponse.builder()
                .status(Status.DELETED.getStatus())
                .error(false)
                .message(Status.DELETED.getMessage())
                .build();
    }
}