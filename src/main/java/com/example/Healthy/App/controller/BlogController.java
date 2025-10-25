package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.BlogDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @PostMapping
    public BaseResponse<BlogDto> createBlog(@RequestBody BlogDto blogDto){
        BlogDto createdBlog = blogService.createBlog(blogDto);
        return BaseResponse.<BlogDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Blog created")
                .data(createdBlog)
                .build();
    }
    @GetMapping
    public BaseResponse<List<BlogDto>> getAllBlogs(){
        List<BlogDto> blogs = blogService.getAllBlogs();
        return BaseResponse.<List<BlogDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(blogs)
                .build();
    }
    @GetMapping("/{id}")
    public BaseResponse<BlogDto> getBlogById(@PathVariable Integer id){
        BlogDto blog = blogService.getBlogByID(id);
        return BaseResponse.<BlogDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(blog)
                .build();
    }
    @PutMapping("/{id}")
    public BaseResponse<BlogDto> updateBlog(@PathVariable Integer id, @RequestBody BlogDto blogDto){
        BlogDto updatedBlog = blogService.updateBlog(id, blogDto);
        return BaseResponse.<BlogDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updatedBlog)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseResponse deleteBlog(@PathVariable Integer id){
        blogService.deleteBlog(id);
        return BaseResponse.builder()
                .status(Status.DELETED.getStatus())
                .error(false)
                .message(Status.DELETED.getMessage())
                .build();
    }
}