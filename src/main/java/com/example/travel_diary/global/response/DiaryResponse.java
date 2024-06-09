package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.domain.type.Scope;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record DiaryResponse(
        Long id,
        String title,
        LocalDate date,
        Scope scope,
        String country,
        List<PhotoResponseDto> photos // 순환 참조 방지 필요 (dto 로 분리 필요)
) {
    public static DiaryResponse from(Diary diary) {
        return new DiaryResponse(diary.getId()
                , diary.getTitle()
                , diary.getDate()
                , diary.getScope()
                , diary.getCountry()
                , diary.getPhotos().stream().map(photo
                -> new PhotoResponseDto(photo.getId(), photo.getPath())).toList());
    }
}
