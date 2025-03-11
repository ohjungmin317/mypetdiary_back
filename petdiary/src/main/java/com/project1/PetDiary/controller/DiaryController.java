package com.project1.PetDiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project1.PetDiary.model.Diary;
import com.project1.PetDiary.service.DiaryService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 모든 일기 가져오기
    @GetMapping("/creatediary")
    public List<Diary> getAllDiaries() {
        return diaryService.getAllDiaries();
    }

    // ID로 일기 가져오기
    @GetMapping("/{id}")
    public ResponseEntity<Diary> getDiaryById(@PathVariable Long id) {
        Optional<Diary> diary = diaryService.getDiaryById(id);
        return diary.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 일기 추가 (글)
    @PostMapping("/creatediary")
    public ResponseEntity<Diary> createDiary(@RequestBody Diary diary) {
        Diary createdDiary = diaryService.createDiary(diary);
        System.out.println(diary.toString());
        return new ResponseEntity<>(createdDiary, HttpStatus.CREATED);
    }

    // 일기 추가 (이미지 - fix)
    @PostMapping("/creatediary/image/{id}")
    public ResponseEntity<Diary> createDiaryWithImage(@PathVariable Long id, @RequestPart("image") MultipartFile image) {
        String photoUrl = diaryService.saveImageAndGetUrl(image);
        Diary createdDiary = diaryService.createDiaryWithImage(id, photoUrl);
        return new ResponseEntity<>(createdDiary, HttpStatus.CREATED);
    }

    // 일기 수정
    @PutMapping("/creatediary/{id}")
    public ResponseEntity<Diary> updateDiary(@PathVariable Long id, @RequestBody Diary updatedDiary) {
        try {
            Diary updated = diaryService.updateDiary(id, updatedDiary);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 일기 삭제
    @DeleteMapping("/creatediary/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);
        return ResponseEntity.noContent().build();
    }
}
