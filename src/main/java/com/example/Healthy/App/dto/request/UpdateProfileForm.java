package com.example.Healthy.App.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProfileForm {
    private Double age;
    private boolean gender;
    private double weight;
    private double height;
    private Double activityLevel;
    private Double goal;
}
