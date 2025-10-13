package com.example.Healthy.App.dto;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class UserDto {
    private Integer id;

    private String name;

    private Double age;

    private boolean gender;

    private Double height;

    private Double weight;

    private Double levelActivity;

    private Double goal;

    private String email;

    private Long phoneNumber;

    private Double bmi;

    private Double bmr;

    private Double tdee;
}
