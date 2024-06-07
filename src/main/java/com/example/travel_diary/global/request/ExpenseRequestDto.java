package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseRequestDto (
    int cost,
    String place,
    LocalDate date,
    String category,
    String scope,
    String country


    ){
    public Expense toEntity(){
        return Expense.builder()
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
