package com.example.travel_diary.service;

import com.example.travel_diary.global.request.ExpenseDetailRequestDto;
import com.example.travel_diary.global.response.ExpenseDetailResponseDto;

public interface ExpenseDetailService {
    void saveExpenseDetail(ExpenseDetailRequestDto requestDto);
    ExpenseDetailResponseDto getExpenseDetailById(Long id);
    void updateExpenseDetail(Long id, ExpenseDetailRequestDto requestDto);
    void deleteExpenseDetailById(Long id);
}
