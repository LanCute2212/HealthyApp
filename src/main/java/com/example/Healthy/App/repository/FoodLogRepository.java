package com.example.Healthy.App.repository;
import com.example.Healthy.App.model.FoodLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FoodLogRepository extends JpaRepository<FoodLog, Integer> {
}
