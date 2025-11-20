package com.example.Healthy.App.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    public String callGemini(String userMessage, String contextInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        String finalPrompt =
                "Bạn là chuyên gia dinh dưỡng 'Healthy AI'. "
                        + "Thông tin người dùng: " + contextInfo + ". "
                        + "Hãy trả lời ngắn gọn, thân thiện: " + userMessage;

        // 2. Build request JSON
        ObjectNode root = mapper.createObjectNode();
        ArrayNode contents = root.putArray("contents");
        ObjectNode contentNode = contents.addObject();
        ArrayNode partsArr = contentNode.putArray("parts");
        partsArr.addObject().put("text", finalPrompt);

        // 3. Send request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(root.toString(), headers);

        try {
            ResponseEntity<String> response =
                    restTemplate.postForEntity(GEMINI_URL + apiKey, request, String.class);

            JsonNode body = mapper.readTree(response.getBody());

            // check lỗi
            if (body.has("error")) {
                return "Lỗi AI: " + body.path("error").path("message").asText();
            }

            // an toàn
            JsonNode textNode =
                    body.path("candidates")
                            .path(0)
                            .path("content")
                            .path("parts")
                            .path(0)
                            .path("text");

            if (!textNode.isMissingNode()) {
                return textNode.asText();
            }

            return "AI không trả về nội dung hợp lệ.";
        } catch (Exception e) {
            return "Xin lỗi, AI đang bận. Vui lòng thử lại sau. Chi tiết: " + e.getMessage();
        }
    }
}
