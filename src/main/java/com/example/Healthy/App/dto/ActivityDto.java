package com.example.Healthy.App.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ActivityDto {
    private Integer id;

    private String name;

    private Double caloriesBurned;

}

