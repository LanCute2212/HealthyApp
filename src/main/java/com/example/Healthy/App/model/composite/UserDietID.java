package com.example.Healthy.App.model.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class UserDietID implements Serializable {
    private Integer userId;
    private Integer dietId;
}