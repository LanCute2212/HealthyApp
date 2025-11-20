package com.example.Healthy.App.dto.openfoodfacts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffNutriments {

    @JsonProperty("energy-kcal_100g")
    private Double calories;

    @JsonProperty("proteins_100g")
    private Double protein;

    @JsonProperty("carbohydrates_100g")
    private Double carbs;

    @JsonProperty("fat_100g")
    private Double fat;

    @JsonProperty("fiber_100g")
    private Double fiber;
}