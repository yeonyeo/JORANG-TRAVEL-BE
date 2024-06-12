package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.response.PhotoResponseDto;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    // 저장
    void insert(String[] paths, Long diaryDetailId) throws IOException;
    Photo getById(Long id);
    // 여행기별 사진 가져오기
    List<Photo> getByDiaryDetailId(Long diaryDetailId);
    // 사진 수정
    void update(Long id, String path);
    // 삭제
    void deleteById(Long id);
}
