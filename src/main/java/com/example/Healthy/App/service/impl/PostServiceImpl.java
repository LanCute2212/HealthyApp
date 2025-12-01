package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.request.CreatePostRequest;
import com.example.Healthy.App.dto.PostDetailDto;
import com.example.Healthy.App.dto.PostSummaryDto;
import com.example.Healthy.App.mapper.PostMapper;
import com.example.Healthy.App.model.Category;
import com.example.Healthy.App.model.Post;
import com.example.Healthy.App.model.User;
import com.example.Healthy.App.repository.CategoryRepository;
import com.example.Healthy.App.repository.PostRepository;
import com.example.Healthy.App.repository.UserRepository;
import com.example.Healthy.App.service.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostSummaryDto> getAllPostSummaries() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(postMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDetailDto getPostDetail(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return postMapper.toDetailDto(post);
    }

    @Override
    public PostDetailDto createPost(CreatePostRequest request, Integer authorId) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());
        post.setAuthor(author);
        post.setCategory(category);

        Post savedPost = postRepository.save(post);

        return postMapper.toDetailDto(savedPost);
    }
    @Override
    public PostDetailDto updatePost(Integer id, CreatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());
        Post updatedPost = postRepository.save(post);
        return postMapper.toDetailDto(updatedPost);
    }

    @Override
    public List<PostSummaryDto> getPostsByCategory(Integer categoryId) {
        List<Post> posts = postRepository.findByCategoryIdOrderByCreatedAtDesc(categoryId);
        return posts.stream().map(postMapper::toSummaryDto).collect(Collectors.toList());
    }
    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}