package com.example.travel_diary.global.response;

import com.example.travel_diary.global.domain.entity.Expense;

import java.time.LocalDate;
import java.util.UUID;

public record ExpenseResponseDto (
    Long id,
    int cost,
    String place,
    LocalDate date,
    String category,
    String scope,
    String country
    ){
    public static ExpenseResponseDto from(Expense expense) {
        return new ExpenseResponseDto(
                expense.getId(),
                expense.getCost(),
                expense.getPlace(),
                expense.getDate(),
                expense.getCategory(),
                expense.getScope(),
                expense.getCountry()
        );
    }
}
