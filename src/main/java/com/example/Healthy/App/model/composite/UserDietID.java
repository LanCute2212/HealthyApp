package com.example.Healthy.App.model.composite;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class UserDietID implements Serializable {

  private Integer userId;
  private Integer dietId;
}