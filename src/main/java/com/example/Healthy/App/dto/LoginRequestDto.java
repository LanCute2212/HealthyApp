package com.example.Healthy.App.dto;
import lombok.*;
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class LoginRequestDto {
    private String email;
    private String password;
}
