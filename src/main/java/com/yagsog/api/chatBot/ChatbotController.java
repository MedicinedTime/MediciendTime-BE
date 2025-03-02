package com.yagsog.api.chatBot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
public class ChatbotController {
    private final ChatbotService chatbotService;

    // 대화 내역 저장
    @PostMapping
    public ResponseEntity<Map<String, String>> chat(@CookieValue(value = "USER_COOKIE") String cookieId,
                                        @RequestBody Map<String, String> requestData) {
        Chatbot chat = chatbotService.saveChat(cookieId, requestData.get("question"));

        Map<String, String> response = new HashMap<>();
        String answer = chat.getAnswer(); // answer 가공 필요
        response.put("answer", chat.getAnswer());
        return ResponseEntity.ok(response);
    }

    // 대화 내역 출력
    @GetMapping("/history")
    public ResponseEntity<ChatBotResponseDto> getHistory(@CookieValue(value = "USER_COOKIE") String cookieId) {

        if (cookieId == null) {
            return ResponseEntity.badRequest().body(new ChatBotResponseDto(List.of()));
        }

        return ResponseEntity.ok(chatbotService.getChatHistory(cookieId));
    }
}
