package com.example.Healthy.App.dto;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FoodLogDto {
    private Integer id;

    private LocalDateTime logTime;

}
