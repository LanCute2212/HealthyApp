package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.BlogDto;
import com.example.Healthy.App.mapper.BlogMapper;
import com.example.Healthy.App.model.Blog;
import com.example.Healthy.App.repository.BlogRepository;
import com.example.Healthy.App.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    @Override
    public List<BlogDto> getAllBlogs() {
        return blogRepository.findAll()
                .stream()
                .map(blogMapper::toDto)
                .toList();
    }

    @Override
    public BlogDto getBlogByID(Integer id) {
        return blogRepository.findById(id)
                .map(blogMapper::toDto)
                .orElseThrow(()->new RuntimeException("Blog not found"));
    }

    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        Blog blog  = blogMapper.toEntity(blogDto);
        Blog savedBlog = blogRepository.save(blog);
        return blogMapper.toDto(savedBlog);
    }

    @Override
    public BlogDto updateBlog(Integer id, BlogDto blogDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(()->new RuntimeException("Blog not found"));
        blogMapper.updateBlogFromDto(blogDto,blog);
        Blog updatedBlog = blogRepository.save(blog);
        return blogMapper.toDto(updatedBlog);
    }

    @Override
    public void deleteBlog(Integer id) {
        blogRepository.deleteById(id);
    }
}
