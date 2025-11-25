package com.example.Healthy.App.service;

import com.example.Healthy.App.dto.PostDetailDto;
import com.example.Healthy.App.dto.PostSummaryDto;
import com.example.Healthy.App.dto.request.CreatePostRequest;

import java.util.List;

public interface PostService {
    List<PostSummaryDto> getAllPostSummaries();

    PostDetailDto getPostDetail(Integer id);

    PostDetailDto createPost(CreatePostRequest request, Integer authorId);
}