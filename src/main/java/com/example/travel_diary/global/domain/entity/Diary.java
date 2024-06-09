package com.example.travel_diary.global.domain.entity;

import com.example.travel_diary.global.domain.type.Scope;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "TITLE", nullable = false)
    @Setter
    private String title;

    @Column(name = "DATE")
    @Setter
    private LocalDate date;

    @Column(name = "SCOPE", nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "COUNTRY", nullable = false)
    @Setter
    private String country;

    @JoinColumn(name = "POST_ID")
    @ManyToOne
    private Post post;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    @Setter
    private List<Photo> photos;
}
