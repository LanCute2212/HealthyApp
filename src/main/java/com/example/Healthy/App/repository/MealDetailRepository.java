package com.example.Healthy.App.repository;
import com.example.Healthy.App.model.MealDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MealDetailRepository extends JpaRepository<MealDetail, Integer> {
}

