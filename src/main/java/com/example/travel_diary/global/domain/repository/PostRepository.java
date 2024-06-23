package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Post> findAllByDiaries_Country(String country, Pageable pageable);
    Page<Post> findAllByCreatedAtBetweenOrderByLoveDesc(LocalDateTime startOfWeek, LocalDateTime endOfWeek, Pageable pageable);
    Page<Post> findAllByDiaries_DateBetween(LocalDate from, LocalDate to, Pageable pageable);
    List<Post> findAllByUser(User user);
}
