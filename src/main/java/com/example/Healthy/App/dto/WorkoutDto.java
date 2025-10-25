package com.example.Healthy.App.dto;
import jakarta.persistence.Column;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkoutDto {

    private String name;

    private String linkVideo;

    private Double duration;

    private Double caloriesBurned;
}
