package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.UserDietDto;
import com.example.Healthy.App.mapper.UserDietMapper;
import com.example.Healthy.App.mapper.UserMapper;
import com.example.Healthy.App.model.User;
import com.example.Healthy.App.model.UserDiet;
import com.example.Healthy.App.repository.UserDietRepository;
import com.example.Healthy.App.repository.UserRepository;
import com.example.Healthy.App.service.UserDietService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDietServiceImpl implements UserDietService {
    private final UserDietMapper userDietMapper;
    private final UserMapper userMapper;
    private final UserDietRepository userDietRepository;
    private final UserRepository userRepository;
    @Override
    public List<UserDietDto> getAllUserDiets() {
        return userDietRepository.findAll()
                .stream()
                .map(userDietMapper::toDto)
                .toList();
    }

    @Override
    public UserDietDto getUserDietByUserID(Integer id) {
//        UserDiet diet = userDietRepository.findByUserId(id).get();
////                .map(userDietMapper::toDto)
////                .orElseThrow(()-> new RuntimeException("User Diet not found"));
//
//        System.out.println(dietDto.getUserDto());

        User u = new User();
        u.setId(id);
        Optional<UserDiet> x = userDietRepository.findByUser(u);
        if (x.isPresent()) {
            return x.map(userDietMapper::toDto)
                .orElseThrow(()-> new RuntimeException("User Diet not found"));
        }
//                .map(userDietMapper::toDto)
//                .orElseThrow(()-> new RuntimeException("User Diet not found"));
        return null;
    }

    @Override
    public UserDietDto createUserDiet(UserDietDto userDietDto) {
        UserDiet userDiet = userDietMapper.toEntity(userDietDto);
        UserDiet savedUserDiet = userDietRepository.save(userDiet);
        return userDietMapper.toDto(savedUserDiet);
    }

    @Override
    public UserDietDto updateUserDiet(Integer id, UserDietDto userDietDto) {
        UserDiet userDiet = userDietRepository.findByUserId(id).orElseThrow(()-> new RuntimeException("User Diet not found"));
        userDietMapper.updateUserDietFromDto(userDietDto, userDiet);
        UserDiet updateUserDiet = userDietRepository.save(userDiet);
        return userDietMapper.toDto(updateUserDiet);
    }

}
