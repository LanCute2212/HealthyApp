package com.example.Healthy.App.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostDetailDto {
    private Integer id;
    private String title;
    private String content;
    private String imageUrl;
    private String categoryName;
    private String authorName;
    private LocalDateTime createdAt;
}