package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.DiaryDetail;
import com.example.travel_diary.global.request.DiaryDetailRequestDto;
import com.example.travel_diary.global.response.DiaryDetailResponseDto;


import java.util.List;

public interface DiaryDetailService {
    Long create(Long diaryId);
    List<DiaryDetail> getAllByDiaryId(Long diaryId);
    void updateDiaryDetail(Long diaryId, DiaryDetailRequestDto req);

//    diary를 지우면 자동으로 삭제 by cascade
    //    void deleteDiaryDetailById(Long id);
}
