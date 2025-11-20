package com.example.Healthy.App.controller;

import com.example.Healthy.App.dto.request.ChatRequest;
import com.example.Healthy.App.dto.response.ChatResponse;
import com.example.Healthy.App.dto.response.BaseResponse;
import com.example.Healthy.App.model.User;
import com.example.Healthy.App.repository.UserRepository;
import com.example.Healthy.App.security.CustomUserDetail;
import com.example.Healthy.App.service.impl.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final GeminiService geminiService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<BaseResponse<ChatResponse>> chatWithAi(
            @RequestBody ChatRequest request,
            @AuthenticationPrincipal CustomUserDetail userDetails) {

        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String userContext = String.format(
                "Tên người dùng: %s,Cân nặng: %.1fkg, Chiều cao: %.1fcm, Tuổi: %.0f, Giới tính: %s, Mục tiêu: %s",
                user.getName(), user.getWeight(), user.getHeight(), user.getAge(), user.getGender(),
                (user.getGoal() != null ? "Giảm cân/Tăng cơ" : "Sống khỏe")
        );

        String aiReply = geminiService.callGemini(request.getMessage(), userContext);

        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setBotMessage(aiReply);

        return ResponseEntity.ok(BaseResponse.<ChatResponse>builder()
                .status(200)
                .error(false)
                .message("Success")
                .data(chatResponse)
                .build());
    }
}