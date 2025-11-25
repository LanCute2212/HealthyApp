package com.example.Healthy.App.dto;

import com.example.Healthy.App.model.Unit;
import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
@Setter
public class DishDto {
    private Integer id;

    private String name;

    private Double carb;

    private Double fat;

    private Double protein;

    private Double fiber;

    private String imageUrl;

    private Double calories;

    private String des;

    private Unit unit;

    private String barcode;

    private String servingSize;
}
