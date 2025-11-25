package com.example.Healthy.App.dto.openfoodfacts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffResponse {
    private int status;
    private OffProduct product;
}