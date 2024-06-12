package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.DiaryDetail;
import com.example.travel_diary.global.domain.type.Scope;

public record DiaryDetailResponseDto (
        Long id,
        String title,
        String content,
        Scope scope,
        String country
) {
    public static DiaryDetailResponseDto from(DiaryDetail diaryDetail) {
        return new DiaryDetailResponseDto (
                diaryDetail.getId()
                , diaryDetail.getTitle()
                , diaryDetail.getContent()
                , diaryDetail.getScope()
                , diaryDetail.getCountry());
    }
}
