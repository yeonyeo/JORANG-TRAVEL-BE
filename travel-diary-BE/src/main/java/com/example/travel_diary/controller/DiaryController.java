package com.example.travel_diary.controller;

import com.example.travel_diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
}
