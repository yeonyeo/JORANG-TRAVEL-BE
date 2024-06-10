package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/photos")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody String path, @RequestBody Long diaryId) {
        photoService.insert(path, diaryId);
    }

    @GetMapping("diaries/{diaryId}")
    public List<Photo> getByDiary(@PathVariable Long diaryId) {
        return photoService.getByDiary(diaryId);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody String path) {
        photoService.update(id, path);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        photoService.deleteById(id);
    }
}
