package com.example.Healthy.App.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileForm {
    private String name;
    private String email;
    private Double age;
    private String gender;
    private double weight;
    private double height;
    private Double activityLevel;
    private Double goal;
    private Double bmi;
    private Double bmr;
    private Double tdee;
}
