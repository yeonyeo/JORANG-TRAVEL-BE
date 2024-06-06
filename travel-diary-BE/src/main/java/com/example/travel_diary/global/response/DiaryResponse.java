package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.type.Scope;

import java.util.Date;

public record DiaryResponse(
        Long id,
        String title,
        Date date,
        Scope scope,
        String country
) {
}
