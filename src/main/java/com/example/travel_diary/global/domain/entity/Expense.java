package com.example.travel_diary.global.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EXPENSES")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXPENSE_ID")
    private Long id;

    @Column(name = "COST")
    private int cost;
    //지출한 곳
    @Column(name = "PLACE")
    private String place;
    //지출 날짜
    @Column( name = "DATE")
    private LocalDate date;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "SCOPE", nullable = false)
    private String scope;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    //지출한 나라
    @Column(name = "COUNTRY")
    private String country;

    @JsonBackReference
    @JoinColumn (name = "POST_ID")
    @ManyToOne
    private Post post;

    public void setCost(int cost) {
        this.cost = cost;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    //set 지우고 /
    // enum scope




}
