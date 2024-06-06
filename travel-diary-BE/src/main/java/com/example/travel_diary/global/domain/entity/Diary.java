package com.example.travel_diary.global.domain.entity;

import com.example.travel_diary.global.domain.type.Scope;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.Date;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DIARIES")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIARY_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "SCOPE")
    private Scope scope;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "COUNTRY")
    private String country;

    @JoinColumn(name = "POST_ID")
    @ManyToOne
    private Post post;
}
