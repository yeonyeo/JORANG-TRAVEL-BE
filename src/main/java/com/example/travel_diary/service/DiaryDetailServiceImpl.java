package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.DiaryDetail;
import com.example.travel_diary.global.domain.repository.DiaryDetailRepository;
import com.example.travel_diary.global.request.DiaryDetailRequestDto;
import com.example.travel_diary.global.response.DiaryDetailResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryDetailServiceImpl implements DiaryDetailService {

    private final DiaryDetailRepository diaryDetailRepository;

    @Override
    @Transactional
    public Long create(Long diaryId) {
        Diary diary = Diary.builder().id(diaryId).build();
        DiaryDetail diaryDetail = DiaryDetail.builder().diary(diary).build();
        return diaryDetailRepository.save(diaryDetail).getId();
    }

    @Override
    public List<DiaryDetail> getAllByDiaryId(Long diaryId) {
        return diaryDetailRepository.findAllByDiary_Id(diaryId);
    }

    @Override
    @Transactional
    public void updateDiaryDetail(Long diaryId, DiaryDetailRequestDto req) {
        DiaryDetail diaryDetail = diaryDetailRepository.findByDiary_Id(diaryId);
        diaryDetail.setTitle(req.title());
        diaryDetail.setContent(req.content());
        diaryDetail.setScope(req.scope());
        diaryDetail.setCountry(req.country());
    }
}
