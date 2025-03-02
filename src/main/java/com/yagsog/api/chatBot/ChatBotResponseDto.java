package com.yagsog.api.chatBot;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ChatBotResponseDto {
    private List<ChatResponseDto> data;
    private int total;

    public ChatBotResponseDto(List<ChatResponseDto> data) {
        this.data = data;
        this.total = data.size();
    }
}

