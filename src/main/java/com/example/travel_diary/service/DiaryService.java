package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.request.DiaryRequestDto;
import com.example.travel_diary.global.response.DiaryResponse;

import java.util.List;

public interface DiaryService {
    Long insertDiary(Post post);
    Diary getById(Long id);
    List<DiaryResponse> getAllByPostId(Long postId);
    void updateDiary(Long id, DiaryRequestDto req);
    void deleteDiaryById(Long id);
}
