package com.example.Healthy.App.repository;
import com.example.Healthy.App.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}
