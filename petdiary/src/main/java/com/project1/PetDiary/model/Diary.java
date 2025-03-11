package com.project1.PetDiary.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String title;
    private String content;

    @Column(name = "created_at")
    private LocalDate createdAt;

    private String petMood; // 반려동물의 기분
    private String weather; // 오늘의 날씨
    private String photoUrl; // 사진 URL

    // 기본 생성자
    public Diary() {
    }
}
