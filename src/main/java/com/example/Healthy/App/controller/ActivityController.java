package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.ActivityDto;
import com.example.Healthy.App.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
    @PostMapping
    public ResponseEntity<ActivityDto> createActivity(@RequestBody ActivityDto activityDto){
        ActivityDto createdActivity = activityService.createActivity(activityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
    }

    @GetMapping
    public ResponseEntity<List<ActivityDto>> getAllActivities(){
        return ResponseEntity.ok(activityService.getAllActivities());
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
