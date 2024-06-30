package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.ExpenseDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseDetailRequestDto(
        int cost,
        String place,
        String category
) {
    public ExpenseDetail toEntity() {
        return ExpenseDetail.builder()
                .cost(cost)
                .place(place)
                .category(category)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
