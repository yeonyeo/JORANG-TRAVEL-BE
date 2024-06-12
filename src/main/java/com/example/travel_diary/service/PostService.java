package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.response.PostResponse;
import org.springframework.security.core.userdetails.UserDetails;

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

}
