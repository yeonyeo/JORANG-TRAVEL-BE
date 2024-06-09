package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Photo;

import java.util.List;

public interface PhotoService {

    // 저장
    void insert(String path, Long diaryId);
    // 여행기별 사진 가져오기
    List<Photo> getByDiary(Long diaryId);

    // 사진 수정
    void update(Long id, String path);
    // 삭제
    void deleteById(Long id);
}
