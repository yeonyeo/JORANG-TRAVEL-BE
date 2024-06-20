package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.ExpenseDetail;
import com.example.travel_diary.global.domain.repository.ExpenseDetailRepository;
import com.example.travel_diary.global.request.ExpenseDetailRequestDto;
import com.example.travel_diary.global.response.ExpenseDetailResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExpenseDetailServiceImpl implements ExpenseDetailService {
    private final ExpenseDetailRepository expenseDetailRepository;

    @Transactional
    @Override
    public void saveExpenseDetail(ExpenseDetailRequestDto requestDto) {
        expenseDetailRepository.save(requestDto.toEntity());
    }

    @Override
    public ExpenseDetailResponseDto getExpenseDetailById(Long id) {
        ExpenseDetail expenseDetail = expenseDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ExpenseDetailResponseDto.from(expenseDetail);
    }

    @Transactional
    @Override
    public void updateExpenseDetail(Long id, ExpenseDetailRequestDto requestDto) {
        ExpenseDetail expenseDetail = expenseDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        expenseDetail.setCost(requestDto.cost());
        expenseDetail.setDate(requestDto.date());
        expenseDetail.setPlace(requestDto.place());
        expenseDetail.setCategory(requestDto.category());
        expenseDetail.setScope(requestDto.scope());
        expenseDetail.setCountry(requestDto.country());
    }

    @Transactional
    @Override
    public void deleteExpenseDetailById(Long id) {
        ExpenseDetail expenseDetail = expenseDetailRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        expenseDetailRepository.deleteById(id);
    }
}
