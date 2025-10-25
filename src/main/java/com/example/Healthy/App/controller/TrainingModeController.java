package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.TrainingModeDto;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.Status;
import com.example.Healthy.App.service.TrainingModeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Training")
public class TrainingModeController {
    private final TrainingModeService trainingModeService;

    public TrainingModeController(TrainingModeService trainingModeService){
        this.trainingModeService = trainingModeService;
    }
    @PostMapping
    public BaseResponse<TrainingModeDto> createTrainingMode(@RequestBody TrainingModeDto trainingModeDto){
        TrainingModeDto createdMode = trainingModeService.createMode(trainingModeDto);
        return BaseResponse.<TrainingModeDto>builder()
                .status(HttpStatus.CREATED.value())
                .error(false)
                .message("Training mode created")
                .data(createdMode)
                .build();
    }
    @GetMapping
    public BaseResponse<List<TrainingModeDto>> getAllModes(){
        List<TrainingModeDto> modes = trainingModeService.getAllModes();
        return BaseResponse.<List<TrainingModeDto>>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(modes)
                .build();
    }
    @GetMapping("/{id}")
    public BaseResponse<TrainingModeDto> getModeById(@PathVariable Integer id){
        TrainingModeDto mode = trainingModeService.getModeByID(id);
        return BaseResponse.<TrainingModeDto>builder()
                .status(HttpStatus.OK.value())
                .error(false)
                .message(".")
                .data(mode)
                .build();
    }
    @PutMapping("/{id}")
    public BaseResponse<TrainingModeDto> updateMode(@PathVariable Integer id, @RequestBody TrainingModeDto trainingModeDto){
        TrainingModeDto updatedMode = trainingModeService.updateMode(id, trainingModeDto);
        return BaseResponse.<TrainingModeDto>builder()
                .status(Status.UPDATED.getStatus())
                .error(false)
                .message(Status.UPDATED.getMessage())
                .data(updatedMode)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseResponse deleteMode(@PathVariable Integer id){
        trainingModeService.deleteMode(id); // Thêm dòng gọi service
        return BaseResponse.builder()
                .status(Status.DELETED.getStatus())
                .error(false)
                .message(Status.DELETED.getMessage())
                .build();
    }
}