package com.example.Healthy.App.service.impl;

import com.example.Healthy.App.dto.DishDto;
import com.example.Healthy.App.dto.openfoodfacts.OffNutriments;
import com.example.Healthy.App.dto.openfoodfacts.OffProduct;
import com.example.Healthy.App.dto.openfoodfacts.OffResponse;
import com.example.Healthy.App.mapper.DishMapper;
import com.example.Healthy.App.model.Dish;
import com.example.Healthy.App.repository.DishRepository;
import com.example.Healthy.App.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final RestTemplate restTemplate = new RestTemplate(); // goi api ngoai
    private final String OFF_API_URL = "https://world.openfoodfacts.org/api/v0/product/";
    @Override
    public List<DishDto> getAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(dishMapper::toDto)
                .toList();
    }

    @Override
    public DishDto getDishByID(Integer id) {
        return dishRepository.findById(id)
                .map(dishMapper::toDto)
                .orElseThrow(()->new RuntimeException("Dish not found"));
    }

    @Override
    public DishDto createDish(DishDto dishDto) {
        Dish dish = dishMapper.toEntity(dishDto);
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.toDto(savedDish);
    }

    @Override
    public DishDto updateDish(Integer id, DishDto dishDto) {
        Dish dish = dishRepository.findById(id).orElseThrow(()->new RuntimeException("Dish not found"));
        dishMapper.updateDishFromDto(dishDto, dish);
        Dish updatedDish = dishRepository.save(dish);
        return dishMapper.toDto(updatedDish);
    }


    @Override
    public DishDto getDishByBarcode(String barcode) {
        Optional<Dish> localDish = dishRepository.findByBarcode(barcode);
        if (localDish.isPresent()) {
            return mapToDto(localDish.get());
        }
        String url = OFF_API_URL + barcode + ".json";
        try {
            OffResponse response = restTemplate.getForObject(url, OffResponse.class);

            if (response != null && response.getStatus() == 1 && response.getProduct() != null) {

                OffProduct offProduct = response.getProduct();

                Dish newDish = new Dish();
                newDish.setBarcode(barcode);
                newDish.setName(offProduct.getProductName());
                newDish.setImageUrl(offProduct.getImageUrl());
                newDish.setServingSize(offProduct.getServingSize());

                if (offProduct.getNutriments() != null) {
                    newDish.setCalories(offProduct.getNutriments().getCalories());
                    newDish.setProtein(offProduct.getNutriments().getProtein());
                    newDish.setFat(offProduct.getNutriments().getFat());
                    newDish.setCarb(offProduct.getNutriments().getCarbs());
                    newDish.setFiber(offProduct.getNutriments().getFiber());
                }

                Dish savedDish = dishRepository.save(newDish);

                return dishMapper.toDto(savedDish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Không tìm thấy sản phẩm với mã: " + barcode);
    }
    private DishDto mapToDto(Dish dish) {
        DishDto dto = new DishDto();

        dto.setId(dish.getId());
        dto.setName(dish.getName());

        dto.setCalories(dish.getCalories());
        dto.setCarb(dish.getCarb());
        dto.setFat(dish.getFat());
        dto.setProtein(dish.getProtein());
        dto.setFiber(dish.getFiber());

        dto.setImageUrl(dish.getImageUrl());
        dto.setDes(dish.getDes());

        dto.setBarcode(dish.getBarcode());
        dto.setServingSize(dish.getServingSize());

        if (dish.getUnit() != null) {
            dto.setUnit(dish.getUnit());
        }
        return dto;
    }
    @Override
    public void deleteDish(Integer id) {
        dishRepository.deleteById(id);
    }
}
