package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Diary;

import java.time.LocalDate;

public record MyDiaryResponseDto(
        String content
) {
    public static MyDiaryResponseDto from(Diary diary){
        return new MyDiaryResponseDto(diary.getContent());
    }
}
