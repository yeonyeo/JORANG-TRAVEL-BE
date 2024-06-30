package com.example.travel_diary.global.request;

import com.example.travel_diary.global.domain.entity.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseRequestDto (
    LocalDate date

//    Long postId



    ){
    public Expense toEntity(Post post){
//        Post post = Post.builder().id(this.postId).build();



    ){
    public Expense toEntity(){

        return Expense.builder()
                .date(date)
                .build();

    }

}
