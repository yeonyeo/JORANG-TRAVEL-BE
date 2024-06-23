package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.type.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByDiaries_Scope(Scope scope, Pageable pageable);
    Page<Post> findByDiaries_ScopeOrderByCreatedAtDesc(Scope scope, Pageable pageable);
//    Page<Post> findAllByDiaries_ScopeAndDiaries_Country(Scope scope, String country, Pageable pageable);
    Page<Post> findAllByDiaries_ScopeAndDiaries_CountryOrderByCreatedAtDesc(Scope scope, String country, Pageable pageable);

    Page<Post> findAllByDiaries_ScopeAndCreatedAtBetweenOrderByLoveDesc(Scope scope, LocalDateTime startOfWeek, LocalDateTime endOfWeek, Pageable pageable);
//    Page<Post> findAllByCreatedAtBetweenOrderByLoveDesc(LocalDateTime startOfWeek, LocalDateTime endOfWeek, Pageable pageable);
    Page<Post> findAllByDiaries_ScopeAndDiaries_DateBetween(Scope scope, LocalDate from, LocalDate to, Pageable pageable);
    Page<Post> findByUser(User user, Pageable pageable);

//    @Query("SELECT DISTINCT p FROM Post p JOIN FETCH p.diaries diaries")
//    List<Post> fetchAll();

}

