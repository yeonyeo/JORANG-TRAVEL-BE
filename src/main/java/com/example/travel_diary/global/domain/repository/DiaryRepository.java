package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<List<Diary>> findAllByPost_Id(Long postId);
}
