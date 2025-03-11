package com.project1.PetDiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project1.PetDiary.model.Diary;
import com.project1.PetDiary.repository.DiaryRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    @Autowired
    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    // 모든 일기 가져오기
    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    // ID로 일기 가져오기
    public Optional<Diary> getDiaryById(Long id) {
        return diaryRepository.findById(id);
    }

    // 일기 추가
    public Diary createDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    // 일기 수정
    public Diary updateDiary(Long id, Diary updatedDiary) {
        return diaryRepository.findById(id).map(diary -> {
            diary.setTitle(updatedDiary.getTitle());
            diary.setContent(updatedDiary.getContent());
            diary.setCreatedAt(updatedDiary.getCreatedAt());
            diary.setPetMood(updatedDiary.getPetMood());
            diary.setWeather(updatedDiary.getWeather());
            diary.setPhotoUrl(updatedDiary.getPhotoUrl());
            return diaryRepository.save(diary);
        }).orElseThrow(() -> new RuntimeException("Diary not found"));
    }

    // 일기 삭제
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

    // 이미지 저장 및 파일명 반환
    public Diary createDiaryWithImage(Long id, String fileName) {
        return diaryRepository.findById(id).map(diary -> {
            diary.setPhotoUrl(fileName);
            return diaryRepository.save(diary);
        }).orElseThrow(() -> new RuntimeException("Diary not found"));
    }

    // 이미지 하드디스크에 저장 후 파일명 반환
    public String saveImageAndGetFileName(MultipartFile image) {
        try {
            String fileName = image.getOriginalFilename();
            File uploadedFile = new File(
                    "C:\\Users\\KT\\Desktop\\Project1\\petdiary\\petdiary\\src\\main\\resources\\static\\" + fileName);
            image.transferTo(uploadedFile);
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }

    public List<Diary> getDiaryByEmail(String email) {
        return diaryRepository.findByEmail(email);
    }
}
