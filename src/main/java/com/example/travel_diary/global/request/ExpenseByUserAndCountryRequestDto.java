package com.example.travel_diary.global.request;

import java.util.UUID;

public record ExpenseByUserAndCountryRequestDto(
        UUID id, String country) {
}
