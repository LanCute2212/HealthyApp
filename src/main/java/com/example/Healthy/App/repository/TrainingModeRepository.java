package com.example.Healthy.App.repository;
import com.example.Healthy.App.model.TrainingMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TrainingModeRepository extends JpaRepository<TrainingMode, Integer> {
}
