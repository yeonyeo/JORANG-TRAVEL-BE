package com.example.travel_diary.global.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryDetailRepository extends JpaRepository<DiaryDetail, Long> {
    DiaryDetail findByDiary_Id(Long diaryId);
    List<DiaryDetail> findAllByDiary_Id(Long diaryId);
}
