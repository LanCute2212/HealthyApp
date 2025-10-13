package com.example.Healthy.App.repository;
import com.example.Healthy.App.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {
}
