package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import org.springframework.data.domain.Page;


import java.time.LocalDate;
import java.util.List;

public interface PostService {
    // 생성
    Long createPost(User user);
    // 전부 가져오기
    List<Post> getAll();
    // 한 개 가져오기
    Post getById(Long id);
    // 삭제
    void deleteById(Long id);
    void update(Long id, String title);
    Page<Post> getAll(int page, int size);
    Page<Post> getRecentPostsFirst(int page, int size);
//    Page<Post> getPostsByCountry(String country, int page, int size);
    Page<Post> getRecentPostsFirstByCountry(String country, int page, int size);

    Page<Post> getTopLikeFirstOnThisWeek(int page, int size);
    Page<Post> getPostsBetween(LocalDate from, LocalDate to, int page, int size);

    Page<Post> getByUser(User user, int page, int size);

}
