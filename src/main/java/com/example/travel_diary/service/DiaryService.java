package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.DiaryRequestDto;

import java.util.List;

public interface DiaryService {
    Long createDiary(Long postId);
    Diary getById(Long id);
    List<Diary> getAllByPostId(Long postId);
    void updateDiary(Long id, DiaryRequestDto req);
    void deleteDiaryById(Long id);
    List<String>getDiaryByUserAndCountry(User user);

//    String summarizeByPostId(Long postId);
//
//    String callPythonScript(List<Diary> dataList);
}
