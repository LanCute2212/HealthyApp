package com.example.Healthy.App.dto.request;

import lombok.Data;

// dto nhận yêu cầu tạo log mới từ client
@Data
public class LogActivityRequest {
    private Integer userId;
    private Integer activityId;
    private double durationInMinutes;
}