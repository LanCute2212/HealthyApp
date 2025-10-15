package com.example.Healthy.App.repository;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.UpdateProfileForm;
import com.example.Healthy.App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<UserDto> getInforByEmail(String email);

}
