package com.example.Healthy.App.mapper;

import com.example.Healthy.App.dto.PostDetailDto;
import com.example.Healthy.App.dto.PostSummaryDto;
import com.example.Healthy.App.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostSummaryDto toSummaryDto(Post post) {
        PostSummaryDto dto = new PostSummaryDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setImageUrl(post.getImageUrl());
        dto.setCreatedAt(post.getCreatedAt());

        if (post.getCategory() != null) {
            dto.setCategoryName(post.getCategory().getName());
        }
        if (post.getAuthor() != null) {
            dto.setAuthorName(post.getAuthor().getName());
        }
        return dto;
    }
    public PostDetailDto toDetailDto(Post post) {
        PostDetailDto dto = new PostDetailDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setImageUrl(post.getImageUrl());
        dto.setCreatedAt(post.getCreatedAt());

        if (post.getCategory() != null) {
            dto.setCategoryName(post.getCategory().getName());
        }
        if (post.getAuthor() != null) {
            dto.setAuthorName(post.getAuthor().getName());
        }
        return dto;
    }
}