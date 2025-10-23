package com.example.Healthy.App.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
public class ActivityLogDto {
    private UserDto userDto;

    private ActivityDto activityDto;

    private Integer logId;

    private Double durationMinutes;

    private LocalDateTime logDate;

    private Double caloriesBurned;
}
