package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.ActivityDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.CreateActivityRequest;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.ActivityService;
import com.example.Healthy.App.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")

public class ActivityController {
    private final ActivityService activityService;
    private final UserService userService;
    public ActivityController(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }
    @PreAuthorize("hasAuthority('User')")
    @PostMapping
    public BaseResponse createActivity(@RequestBody CreateActivityRequest createActivityRequest){
        ActivityDto createdActivity = activityService.createActivity(createActivityRequest);
        return BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Activity created")
                .data(createdActivity)
                .build();
    }
    @GetMapping
    public BaseResponse<List<ActivityDto>> getAllActivities(@RequestParam Integer id){
        UserDto currentUserDto = userService.getUserByID(id);
        List<ActivityDto> activities = activityService.getAllActivities(currentUserDto);
        return BaseResponse.<List<ActivityDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(activities)
                .build();
    }
    @GetMapping("/{id}")
    public BaseResponse<ActivityDto> getActivityByID(@PathVariable Integer id){
        ActivityDto activity = activityService.getActivityByID(id);
        return BaseResponse.<ActivityDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(activity)
                .build();
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{id}")
    public BaseResponse<ActivityDto> updateActivity(@PathVariable Integer id, @RequestBody ActivityDto activityDto){
        ActivityDto updateActivity = activityService.updateActivity(id, activityDto);
        return BaseResponse.<ActivityDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updateActivity)
                .build();
    }
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    public BaseResponse deleteActivity(@PathVariable Integer id){
        activityService.deleteActivity(id);
        return BaseResponse.builder()
                .status(Status.DELETED.getStatus())
                .error(false)
                .message(Status.DELETED.getMessage())
                .build();
    }
}
