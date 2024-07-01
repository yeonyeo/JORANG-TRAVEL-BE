package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.ExpenseDetail;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.ExpenseDetailRepository;
import com.example.travel_diary.global.request.ExpenseDetailRequestDto;
import com.example.travel_diary.global.response.ExpenseDetailByUserAndCountryResponseDto;
import com.example.travel_diary.global.response.ExpenseDetailResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseDetailServiceImpl implements ExpenseDetailService {
    private final ExpenseDetailRepository expenseDetailRepository;

    @Transactional
    @Override
    public void saveExpenseDetailbyExpenseId( List<ExpenseDetailRequestDto> requestDto) {
        requestDto.forEach(e -> expenseDetailRepository.save(e.toEntity()));
//        expenseDetailRepository.save(requestDto.toEntity());
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

    @Override
    public List<ExpenseDetailByUserAndCountryResponseDto> getExpenseDetailByUserAndCountry(User user) {
        List<ExpenseDetail> allAndPostUser = expenseDetailRepository.findAllByExpense_Post_User(user);
        List<String> countryByUser = new ArrayList<>(); // 초기화
        List<ExpenseDetailByUserAndCountryResponseDto> result = new ArrayList<>(); // 초기화
        int total = 0;
        for(ExpenseDetail expenseDetail : allAndPostUser) {
            if(!countryByUser.contains(expenseDetail.getCountry())) {
                countryByUser.add(expenseDetail.getCountry());
            }
        }

        for(String country : countryByUser) {
            List<ExpenseDetail> allByCountryAndPostUser = expenseDetailRepository.findAllByCountryAndExpense_Post_User(country, user);
            total = 0;
            for(ExpenseDetail expenseDetail : allByCountryAndPostUser) {
                total += expenseDetail.getCost();
            }
            result.add(new ExpenseDetailByUserAndCountryResponseDto(country, total));
        }
        return result;
    }
    @Override
    public List<ExpenseDetailResponseDto> getExpenseDetailsByPostId(Long postId) {
        List<ExpenseDetail> expenseDetails = expenseDetailRepository.findAllByExpense_Post_Id(postId);
        return expenseDetails.stream()
                .map(ExpenseDetailResponseDto::from)
                .collect(Collectors.toList());
    }
}
