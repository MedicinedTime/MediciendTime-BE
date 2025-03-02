package com.yagsog.api.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cookieId;

    private String name;
    private String age; // 함수 통합 처리를 위하여 String 처리
    private String gender;

    @Column(columnDefinition = "TEXT")
    private String medications;

    public User(String cookieId) {
        this.cookieId = cookieId;
    }

}
