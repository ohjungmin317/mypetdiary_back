package com.project1.PetDiary.repository;

import com.project1.PetDiary.model.Diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findByEmail(String email);

}