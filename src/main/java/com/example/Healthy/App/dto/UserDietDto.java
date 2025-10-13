package com.example.Healthy.App.dto;
import com.example.Healthy.App.model.User;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDietDto {
    private UserDto userDto;

    private DietDto dietDto;

    private int carbPercent;

    private int fatPercent;

    private int proteinPercent;
}
