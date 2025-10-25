package com.example.Healthy.App.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogDto {

    private String title;

    private String content;

    private LocalDateTime dateCreated;

    private Boolean approved;
}
