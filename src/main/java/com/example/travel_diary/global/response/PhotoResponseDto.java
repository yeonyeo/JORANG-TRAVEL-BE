package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Photo;

public record PhotoResponseDto(
        Long id,
        String path
) {
}
