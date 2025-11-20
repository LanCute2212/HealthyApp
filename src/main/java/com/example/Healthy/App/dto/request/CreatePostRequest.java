package com.example.Healthy.App.dto.request;
import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;
    private String content;
    private String imageUrl;
    private Integer categoryId;
}