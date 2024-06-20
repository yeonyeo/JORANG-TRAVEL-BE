package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.ExpenseDetail;

import java.time.LocalDate;

public record ExpenseDetailResponseDto(
        Long id,
        int cost,
        String place,
        LocalDate date,
        String category,
        String scope,
        String country
) {
    public static ExpenseDetailResponseDto from(ExpenseDetail expenseDetail) {
        return new ExpenseDetailResponseDto(
                expenseDetail.getId(),
                expenseDetail.getCost(),
                expenseDetail.getPlace(),
                expenseDetail.getDate(),
                expenseDetail.getCategory(),
                expenseDetail.getScope(),
                expenseDetail.getCountry()
        );
    }
}
