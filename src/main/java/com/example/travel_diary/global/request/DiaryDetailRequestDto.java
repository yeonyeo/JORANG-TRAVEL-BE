package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.DiaryDetail;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.type.Scope;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiaryDetailRequestDto(
        String title,
        String content,
        Scope scope,
        String country
) {
    public DiaryDetail toEntity(Diary diary) {
        return DiaryDetail.builder()
                .title(title)
                .content(content)
                .scope(scope)
                .country(country)
                .createdAt(LocalDateTime.now())
                .diary(diary)
                .build();
    }
}
