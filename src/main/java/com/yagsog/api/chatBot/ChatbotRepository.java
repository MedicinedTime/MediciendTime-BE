package com.yagsog.api.chatBot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatbotRepository extends JpaRepository<Chatbot, Long> {
    List<Chatbot> findByCookieIdOrderByCreatedAtAsc(String cookieId);
    int countByCookieId(String cookieId);
    List<Chatbot> findByCookieId(String cookieId);
}

