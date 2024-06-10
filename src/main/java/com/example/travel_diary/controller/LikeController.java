package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/posts/{postId}")
    public int likeComment(@AuthenticationPrincipal User user, @PathVariable Long postId) {
//        if(user.) throw new IllegalArgumentException("로그인 필요");
        return likeService.likeComment(user, postId);
    }

    @GetMapping("/posts")
    public List<Like> getPosts(@AuthenticationPrincipal User user) {
        return likeService.getPosts(user);
    }

    @GetMapping("/posts/{postId}/count")
    public Long countLike(@PathVariable Long postId) {
        return likeService.countLike(postId);
    }
}
