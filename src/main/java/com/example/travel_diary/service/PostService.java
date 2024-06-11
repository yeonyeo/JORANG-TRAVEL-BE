package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;

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
    // 좋아요 수 가져오기
    int getLikes(Long id);


}
