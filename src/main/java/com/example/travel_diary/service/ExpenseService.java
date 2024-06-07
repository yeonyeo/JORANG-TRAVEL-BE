package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.request.ExpenseRequestDto;
import com.example.travel_diary.global.response.ExpenseResponseDto;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    void saveExpense(ExpenseRequestDto expenseRequestDto);
    Expense updateExpense(Long id,ExpenseRequestDto expenseRequestDto);
    ExpenseResponseDto getExpenseById(Long id);
    void deleteExpenseById(Long id);
//    List<Expense> getAllExpense();



}
