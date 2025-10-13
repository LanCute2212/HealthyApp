package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.LoginRequestDto;
import com.example.Healthy.App.dto.RegisterDto;
import com.example.Healthy.App.dto.UserDto;
import com.example.Healthy.App.mapper.UserMapper;
import com.example.Healthy.App.model.Role;
import com.example.Healthy.App.model.User;
import com.example.Healthy.App.repository.UserRepository;
import com.example.Healthy.App.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
                .orElseThrow(()-> new RuntimeException("User not found"));
    }

    @Override
    public UserDto createUser(RegisterDto registerDto) {
        Optional<User> u = userRepository.findByEmail(registerDto.getEmail());
        if(u.isPresent()){
            throw new RuntimeException("Email existed");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setRole(new Role(1, null, null));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(Integer id, UserDto userDto) {
        calculate(userDto);
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        userMapper.updateUserFromDto(userDto, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    private void calculate(UserDto userDto) {
        Double bmr;
        Double tdee;
        Double weight = userDto.getWeight();
        Double height = userDto.getHeight();
        Double age = userDto.getAge();
        Double levelActivity = userDto.getLevelActivity();
        boolean gender = userDto.isGender();

        if(gender) {
            bmr = 10*weight+6.25*height-5*age+5;
        } else {
            bmr = 10*weight+6.25*height-5*age-161;
        }

        tdee = bmr * levelActivity;
        Double bmi = weight/Math.pow(height/100, 2);

        userDto.setBmr(bmr);
        userDto.setTdee(tdee);
        userDto.setBmi(bmi);
    }
    @Override
    public UserDto loginUser(LoginRequestDto loginRequestDto){
        Optional<User> u = userRepository.findByEmail(loginRequestDto.getEmail());
        if(!u.isPresent()){
            throw new RuntimeException("Email not exist");
        }
        if(u.get().getPassword().equals(loginRequestDto.getPassword())){
            return userMapper.toDto(u.get());
        }
        throw new RuntimeException("Password incorrect");
    }
    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
