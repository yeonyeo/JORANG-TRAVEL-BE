package com.example.travel_diary.global.domain.entity;

import com.example.travel_diary.global.domain.type.Scope;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DIARY_DETAILS")
public class DiaryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIARY_DETAIL_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    @Setter
    private String title;

    @Column(name = "CONTENT", nullable = false)
    @Setter
    private String content;

    @Column(name = "SCOPE", nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "COUNTRY", nullable = false)
    @Setter
    private String country;

    @JsonBackReference
    @JoinColumn(name = "DIARY_ID")
    @ManyToOne
    private Diary diary;

    @JsonManagedReference
    @OneToMany(mappedBy = "diaryDetail", cascade = CascadeType.ALL)
    private List<Photo> photos;
}
