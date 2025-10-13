package com.example.Healthy.App.repository;
import com.example.Healthy.App.model.User;
import com.example.Healthy.App.model.UserDiet;
import com.example.Healthy.App.model.composite.UserDietID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDietRepository extends JpaRepository<UserDiet, UserDietID> {
    @Query (value = "SELECT * FROM user_diet WHERE user_id = :id", nativeQuery = true)
    public Optional<UserDiet> findByUserId(@Param("id") Integer id);

    public Optional<UserDiet> findByUser(User user);
}
