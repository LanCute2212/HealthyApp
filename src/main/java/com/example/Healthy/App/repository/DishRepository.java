package com.example.Healthy.App.repository;
import com.example.Healthy.App.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    Optional<Dish> findByBarcode(String barcode);
}
