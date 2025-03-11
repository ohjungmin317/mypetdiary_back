package com.project1.PetDiary.repository;

import com.project1.PetDiary.model.Diary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    
}