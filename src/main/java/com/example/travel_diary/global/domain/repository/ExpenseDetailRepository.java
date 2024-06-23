package com.example.travel_diary.global.domain.repository;

import com.example.travel_diary.global.domain.entity.ExpenseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseDetailRepository extends JpaRepository<ExpenseDetail, Long> {
}
