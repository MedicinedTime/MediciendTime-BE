package com.yagsog.api.chatBot;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Chatbot")
public class Chatbot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cookieId;  // 쿠키 기반 사용자 식별

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;  // 사용자가 입력한 메시지

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;  // 챗봇 응답

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // 메시지 시간 저장
}

