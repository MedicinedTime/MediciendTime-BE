package com.yagsog.api.chatBot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponseDto {
    private String id;
    private String question;
    private String answer;

    public ChatResponseDto(Long id, String question, String answer) {
        this.id = String.valueOf(id);
        this.question = question;
        this.answer = answer;
    }
}