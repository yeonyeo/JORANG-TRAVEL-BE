package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.type.Scope;

import java.time.LocalDateTime;
import java.util.Date;

public record DiaryRequestDto(
        String title,
        Date date,
        Scope scope,
        String country
) {
    public Diary toEntity() {
        return Diary.builder()
                .title(title)
                .date(date)
                .scope(scope)
                .country(country)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
