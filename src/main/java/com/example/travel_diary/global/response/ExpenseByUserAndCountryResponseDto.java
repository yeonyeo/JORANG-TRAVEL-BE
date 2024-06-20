package com.example.travel_diary.global.response;

public record ExpenseByUserAndCountryResponseDto(
        String country, int cost
) {
}
