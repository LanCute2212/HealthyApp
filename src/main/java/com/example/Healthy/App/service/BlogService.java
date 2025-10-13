package com.example.Healthy.App.service;

import com.example.Healthy.App.dto.BlogDto;
import java.util.List;

public interface BlogService {
    List<BlogDto> getAllBlogs();
    BlogDto getBlogByID(Integer id);
    BlogDto createBlog(BlogDto blogDto);
    BlogDto updateBlog(Integer id, BlogDto blogDto);
    void deleteBlog(Integer id);
}
