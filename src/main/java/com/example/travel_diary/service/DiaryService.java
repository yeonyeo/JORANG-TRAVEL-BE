package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.DiaryRequestDto;
import com.example.travel_diary.global.response.MyDiaryResponseDto;

import java.util.List;

public interface DiaryService {
    Long createDiary(Long postId);
    Diary getById(Long id);
    List<Diary> getAllByPostId(Long postId);
    void deleteDiaryById(Long id);
    void updateDiary(List<DiaryRequestDto> req);
//    List<String> getDiaryByUserAndCountry(User user);

    List<MyDiaryResponseDto> getDiaryByUser(User user);
}
