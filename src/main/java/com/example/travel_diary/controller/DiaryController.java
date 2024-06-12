package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createDiary(@RequestBody Long postId) {
        return diaryService.createDiary(postId);
    }

    @GetMapping("/posts/{postId}")
    public List<Diary> getAllByPostId(@PathVariable Long postId) {
        return diaryService.getAllByPostId(postId);
    };

    @GetMapping("/{id}")
    public Diary getById(@PathVariable Long id) {
        return diaryService.getById(id);
    }

    @PutMapping("/{id}")
    public void updateDiary(@PathVariable Long id, @RequestBody LocalDate date) {
        diaryService.updateDiary(id, date);
    };
    @DeleteMapping("/{id}")
    public void deleteDiaryById(@PathVariable Long id) {
        diaryService.deleteDiaryById(id);
    };
}
