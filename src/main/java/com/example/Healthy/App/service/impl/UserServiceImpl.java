package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.request.LoginRequestDto;
import com.example.Healthy.App.dto.RegisterDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.dto.request.ProfileForm;
import com.example.Healthy.App.dto.request.UpdateProfileForm;
import com.example.Healthy.App.dto.response.LoginResponse;
import com.example.Healthy.App.mapper.UserMapper;
import com.example.Healthy.App.model.Role;
import com.example.Healthy.App.model.User;
import com.example.Healthy.App.repository.UserRepository;
import com.example.Healthy.App.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getUserByID(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ProfileForm getInforByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProfileForm response = new ProfileForm();

        response.setName(user.getName() != null ? user.getName() : "");
        response.setEmail(user.getEmail());
        response.setAge(user.getAge() != null ? user.getAge() : 0);
        response.setGender(user.getGender() != null ? user.getGender() : "");
        response.setWeight(user.getWeight() != null ? user.getWeight() : 0.0);
        response.setHeight(user.getHeight() != null ? user.getHeight() : 0.0);
        response.setActivityLevel(user.getLevelActivity() != null ? user.getLevelActivity() : 0.0);
        response.setGoal(user.getGoal() != null ? user.getGoal() : 0.0);

        response.setBmi(user.getBmi() != null ? user.getBmi() : 0.0);
        response.setBmr(user.getBmr() != null ? user.getBmr() : 0.0);
        response.setTdee(user.getTdee() != null ? user.getTdee() : 0.0);

        return response;
    }


    @Override
    public void updateProfile(String email, UpdateProfileForm form) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        user.setGender(form.isGender() ? "male" : "female");

        user.setWeight(form.getWeight());

        user.setHeight(form.getHeight());

        user.setGoal(form.getGoal());

        user.setAge(form.getAge());

        user.setLevelActivity(form.getActivityLevel());

        UserDto dto = userMapper.toDto(user);

        calculate(dto);

        userRepository.save(user);
    }

    @Override
    public UserDto createUser(RegisterDto registerDto) {
        Optional<User> u = userRepository.findByEmail(registerDto.getEmail());
        if (u.isPresent()) {
            throw new RuntimeException("Email existed");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        user.setRole(new Role(1, null, null));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        calculate(userDto);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUserFromDto(userDto, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    private void calculate(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Double bmr;
        Double tdee;
        Double weight = userDto.getWeight();
        Double height = userDto.getHeight();
        Double age = userDto.getAge();
        Double levelActivity = userDto.getLevelActivity();
        boolean gender = userDto.isGender();

        if (gender) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }

        tdee = bmr * levelActivity;
        Double bmi = weight / Math.pow(height / 100, 2);

        user.setBmr(bmr);
        user.setTdee(tdee);
        user.setBmi(bmi);
    }

    @Override
    public LoginResponse loginUser(LoginRequestDto loginRequestDto) {
        Optional<User> u = userRepository.findByEmail(loginRequestDto.getEmail());
        if (!u.isPresent()) {
            throw new RuntimeException("Email not exist");
        }
        if (u.get().getPassword().equals(loginRequestDto.getPassword())) {
            userMapper.toDto(u.get());
            return LoginResponse.builder()
                .userId(u.get().getId())
                .email(u.get().getEmail())
                .build();
        }
        throw new RuntimeException("Password incorrect");
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
