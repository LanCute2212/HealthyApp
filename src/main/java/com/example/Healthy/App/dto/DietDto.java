package com.example.Healthy.App.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class DietDto {
    private Integer id;

    private String name;

    private int carbPercent;

    private int fatPercent;

    private int proteinPercent;
}
