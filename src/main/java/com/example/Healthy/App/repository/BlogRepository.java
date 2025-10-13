package com.example.Healthy.App.repository;
import com.example.Healthy.App.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
