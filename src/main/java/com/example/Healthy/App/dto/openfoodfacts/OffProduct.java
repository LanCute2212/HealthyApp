package com.example.Healthy.App.dto.openfoodfacts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Bỏ qua các trường thừa
public class OffProduct {

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("serving_size")
    private String servingSize;

    @JsonProperty("nutriments")
    private OffNutriments nutriments;
}