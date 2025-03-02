package com.yagsog.api.chatBot;

import java.util.List;

public interface ChatbotService {
    Chatbot saveChat(String cookieId, String userMessage);
    public ChatBotResponseDto getChatHistory(String userCookie);
    int getChatHistoryCount(String cookieId);
}
