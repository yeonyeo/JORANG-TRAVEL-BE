package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Photo;

public record PhotoResponseDto(
        Long id,
        String path
) {
    public static PhotoResponseDto from(Photo photo) {
        return new PhotoResponseDto(photo.getId(), photo.getPath());
    }
}
