package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.DiaryDetail;
import com.example.travel_diary.global.request.DiaryDetailRequestDto;
import com.example.travel_diary.service.DiaryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diaryDetails")
@RequiredArgsConstructor
public class DiaryDetailController {
    private final DiaryDetailService diaryDetailService;

    @PostMapping("diary/{diaryId}")
    public Long create(@PathVariable Long diaryId) {
        return diaryDetailService.create(diaryId);
    }

    @GetMapping("diary/{diaryId}")
    public List<DiaryDetail> getAllByDiaryId(@PathVariable Long diaryId) {
        return diaryDetailService.getAllByDiaryId(diaryId);
    }

    @PutMapping("diary/{diaryId}")
    public void updateDiaryDetail(@PathVariable Long diaryId, @RequestBody DiaryDetailRequestDto req) {
        diaryDetailService.updateDiaryDetail(diaryId, req);
    }
}
