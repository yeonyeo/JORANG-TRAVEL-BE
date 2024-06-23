package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.ExpenseDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseDetailRequestDto(
        int cost,
        String place,
        LocalDate date,
        String category,
        String scope,
        String country
) {
    public ExpenseDetail toEntity() {
        return ExpenseDetail.builder()
                .cost(cost)
                .place(place)
                .date(date)
                .category(category)
                .scope(scope)
                .country(country)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
