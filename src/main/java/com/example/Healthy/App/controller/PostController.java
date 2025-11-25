package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.request.CreatePostRequest;
import com.example.Healthy.App.dto.PostDetailDto;
import com.example.Healthy.App.dto.PostSummaryDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.security.CustomUserDetail;
import com.example.Healthy.App.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<PostSummaryDto>>> getAllPosts() {
        List<PostSummaryDto> list = postService.getAllPostSummaries();

        BaseResponse<List<PostSummaryDto>> response = BaseResponse.<List<PostSummaryDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message("Get all posts successfully")
                .data(list)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PostDetailDto>> getPostDetail(@PathVariable Integer id) {
        PostDetailDto postDetail = postService.getPostDetail(id);
        BaseResponse<PostDetailDto> response = BaseResponse.<PostDetailDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message("Get post detail successfully")
                .data(postDetail)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<PostDetailDto>> createPost(
            @RequestBody CreatePostRequest request,
            @AuthenticationPrincipal CustomUserDetail userDetails) {
        PostDetailDto newPost = postService.createPost(request, userDetails.getId());
        BaseResponse<PostDetailDto> response = BaseResponse.<PostDetailDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Post created successfully")
                .data(newPost)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}