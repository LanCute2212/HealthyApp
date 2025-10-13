package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.BlogDto;
import com.example.Healthy.App.service.BlogService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDto){
        BlogDto createdBlog = blogService.createBlog(blogDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
    }
    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs(){
        return  ResponseEntity.ok(blogService.getAllBlogs());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Integer id){
        return  ResponseEntity.ok(blogService.getBlogByID(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Integer id, @RequestBody BlogDto blogDto){
        BlogDto updatedBlog = blogService.updateBlog(id, blogDto);
        return ResponseEntity.ok(updatedBlog);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Integer id){
        return ResponseEntity.ok("Deleted blog with id: " +id);
    }

}
