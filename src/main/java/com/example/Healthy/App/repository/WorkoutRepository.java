package com.example.Healthy.App.repository;

import com.example.Healthy.App.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    List<Workout> findByTrainingModeId(Integer trainingModeId);
}
