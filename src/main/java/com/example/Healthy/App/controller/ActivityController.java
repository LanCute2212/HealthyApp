package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.ActivityDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.CreateActivityRequest;
import com.example.Healthy.App.service.ActivityService;
import com.example.Healthy.App.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping
    public ResponseEntity<ActivityDto> createActivity(@RequestBody CreateActivityRequest createActivityRequest){
        ActivityDto createdActivity = activityService.createActivity(createActivityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
    }

    @GetMapping
    public ResponseEntity<List<ActivityDto>> getAllActivities(@RequestParam Integer id){
        UserDto currentUserDto = userService.getUserByID(id);
        List<ActivityDto> activities = activityService.getAllActivities(currentUserDto);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDto> getActivityByID(@PathVariable Integer id){
        return ResponseEntity.ok(activityService.getActivityByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDto> updateActivity(@PathVariable Integer id, @RequestBody ActivityDto activityDto){
        ActivityDto updateActivity = activityService.updateActivity(id, activityDto);
        return ResponseEntity.ok(updateActivity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable Integer id){
        activityService.deleteActivity(id);
        return ResponseEntity.ok("Deleted activity with ID: "+ id);
    }
}
