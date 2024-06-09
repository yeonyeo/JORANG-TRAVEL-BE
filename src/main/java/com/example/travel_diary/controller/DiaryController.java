package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.request.DiaryRequestDto;
import com.example.travel_diary.global.response.DiaryResponse;
import com.example.travel_diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping("/posts/{postId}")
    public List<DiaryResponse> getAllByPostId(@PathVariable Long postId) {
        return diaryService.getAllByPostId(postId);
    };
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertDiary(@RequestBody DiaryRequestDto req) {
        diaryService.insertDiary(req);
    }
    @PutMapping("/{id}")
    public void updateDiary(@PathVariable Long id, @RequestBody DiaryRequestDto req) {
        diaryService.updateDiary(id, req);
    };
    @DeleteMapping("/{id}")
    public void deleteDiaryById(@PathVariable Long id) {
        diaryService.deleteDiaryById(id);
    };
}
