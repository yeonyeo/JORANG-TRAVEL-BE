package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Like;

public record LikeResponse(
        Long id, Long postId
) {
    public static LikeResponse from(Like like) {
        return new LikeResponse(like.getId(), like.getPost().getId());
    }
}
