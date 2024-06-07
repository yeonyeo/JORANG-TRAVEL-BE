package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
