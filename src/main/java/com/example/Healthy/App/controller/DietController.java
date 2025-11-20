package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.DietDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.DietService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diets")
public class DietController {

    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping
    public BaseResponse<List<DietDto>> getAllDiets() {
        List<DietDto> diets = dietService.getAllDiets();
        return BaseResponse.<List<DietDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(diets)
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<DietDto> getDietByID(@PathVariable Integer id) {
        DietDto diet = dietService.getDietByID(id);
        return BaseResponse.<DietDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(diet)
                .build();
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public BaseResponse<DietDto> createDiet(@RequestBody DietDto dietDto) {
        DietDto createdDiet = dietService.createDiet(dietDto);
        return BaseResponse.<DietDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Diet created")
                .data(createdDiet)
                .build();
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{id}")
    public BaseResponse<DietDto> updateDiet(@PathVariable Integer id, @RequestBody DietDto dietDto) {
        DietDto updatedDiet = dietService.updateDiet(id, dietDto);
        return BaseResponse.<DietDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updatedDiet)
                .build();
    }
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    public BaseResponse deleteDiet(@PathVariable Integer id) {
        dietService.deleteDiet(id);
        return BaseResponse.builder()
                .status(Status.DELETED.getStatus())
                .error(false)
                .message(Status.DELETED.getMessage())
                .build();
    }
}