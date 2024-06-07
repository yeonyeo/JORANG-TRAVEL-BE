package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.request.ExpenseRequestDto;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import com.example.travel_diary.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public void save(@RequestBody ExpenseRequestDto req) {
        expenseService.saveExpense(req);
    }
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ExpenseRequestDto req) {
        expenseService.updateExpense(id, req);
    }
    @GetMapping("/{id}")
    public ExpenseResponseDto getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }





    @DeleteMapping("/{id}")
    public void deleteExpenseById(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
    }




}
