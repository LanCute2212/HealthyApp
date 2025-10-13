package com.example.Healthy.App.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
public class ActivityLogDto {
    private Integer logID;

    private Double duration;

    private LocalDateTime dateLog;
}
