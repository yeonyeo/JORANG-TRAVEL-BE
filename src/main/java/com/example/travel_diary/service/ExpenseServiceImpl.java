package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Expense;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.ExpenseRepository;
import com.example.travel_diary.global.domain.repository.UserRepository;
import com.example.travel_diary.global.exception.UserNotFoundException;
import com.example.travel_diary.global.request.ExpenseByUserAndCountryRequestDto;
import com.example.travel_diary.global.request.ExpenseRequestDto;
import com.example.travel_diary.global.response.ExpenseByUserAndCountryResponseDto;
import com.example.travel_diary.global.response.ExpenseResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Transactional
    @Override
    public void saveExpense(ExpenseRequestDto expenseRequestDto) {
       expenseRepository.save(expenseRequestDto.toEntity());
    }

    @Override
    public ExpenseResponseDto getExpenseById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        // 에러처리(orelseget)
        return ExpenseResponseDto.from(expense);
    }
    @Transactional
    @Override
    public Expense updateExpense(Long id, ExpenseRequestDto req){
        Expense expense = expenseRepository.findById(id).orElseThrow(
                EntityNotFoundException::new);
        expense.setCost(req.cost());
        expense.setDate(req.date());
        expense.setPlace(req.place());
        expense.setCategory(req.category());
        expense.setScope(req.scope());
        expense.setCountry(req.country());

        //set이란 save 중 하나만
        //save를 하면 중복된 값 생길수도?insert와 update의 구분 기준이 없음 -> 어떤 기준?
        // set을 쓰면 @Transactional //더티체킹-> 원본데이터
        return expense;
    }
    @Transactional
    @Override
    public void deleteExpenseById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
        expenseRepository.deleteById(id);
    }

    @Override
    public List<ExpenseByUserAndCountryResponseDto> getExpenseByUserAndCountry(@AuthenticationPrincipal User user, String country) {
        List<Expense> allByCountryAndPostUser = expenseRepository.findAllByCountryAndPost_User(country, user);
        List<ExpenseByUserAndCountryResponseDto> result = new ArrayList<>(); // 초기화
        for(Expense expense : allByCountryAndPostUser) {
            result.add(new ExpenseByUserAndCountryResponseDto(expense.getCountry(), expense.getCost()));
        }
//        for(ExpenseByUserAndCountryResponseDto dto : result) {
//            System.out.println("---------------------result--------"+dto.country()+dto.cost());
//        }
        return result;
    }


//@Transactional지우다가뭔가 오류-> 실패->롤백하는게 목표
    //    @Override
//    public List<Expense> getAllExpenses(){
//        return expenseRepository.findAll();
//    }
}
