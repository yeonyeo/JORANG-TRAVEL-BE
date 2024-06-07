package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}

