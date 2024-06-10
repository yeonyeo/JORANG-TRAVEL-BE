package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.response.PhotoResponseDto;

import java.io.IOException;
import java.util.List;

public interface PhotoService {

    // 저장
    void insert(String[] paths, Long diaryId) throws IOException;
    // 여행기별 사진 가져오기
    List<PhotoResponseDto> getByDiaryId(Long diaryId);
    // 사진 수정
    void update(Long id, String path);
    // 삭제
    void deleteById(Long id);
}
