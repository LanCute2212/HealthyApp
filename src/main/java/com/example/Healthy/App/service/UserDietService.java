package com.example.Healthy.App.service;
import com.example.Healthy.App.dto.UserDietDto;
import java.util.List;

public interface UserDietService {
    List<UserDietDto> getAllUserDiets();
    UserDietDto getUserDietByUserID(Integer id);
    UserDietDto createUserDiet(UserDietDto userDietDto);
    UserDietDto updateUserDiet(Integer id, UserDietDto userDietDto);
    //void deleteUserDiet(Integer id);
}
