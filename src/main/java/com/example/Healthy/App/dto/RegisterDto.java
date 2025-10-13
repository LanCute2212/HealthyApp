package com.example.Healthy.App.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegisterDto {
    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;

    @NotBlank(message= "Email không được bỏ trống")
    @Email(message = "Email sai định dạng")
    private String email;
}
