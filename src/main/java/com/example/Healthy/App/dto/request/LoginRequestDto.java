package com.example.Healthy.App.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
public class LoginRequestDto {
  private String email;
  private String password;
}
