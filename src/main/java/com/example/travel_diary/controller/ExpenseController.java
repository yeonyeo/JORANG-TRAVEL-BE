package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.request.ExpenseByUserAndCountryRequestDto;
import com.example.travel_diary.global.request.ExpenseRequestDto;
import com.example.travel_diary.global.response.ExpenseByUserAndCountryResponseDto;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import com.example.travel_diary.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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

    @GetMapping("/mypage")
    public List<ExpenseByUserAndCountryResponseDto> getExpenseByUserAndCountry(@AuthenticationPrincipal User user) {
        return expenseService.getExpenseByUserAndCountry(user);
    }

}
