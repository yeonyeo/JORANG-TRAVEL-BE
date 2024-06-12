package com.example.travel_diary.global.domain.entity;

import com.example.travel_diary.global.domain.type.Scope;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "DATE")
    @Setter
    private LocalDate date;

    @JsonBackReference
    @JoinColumn(name = "POST_ID")
    @ManyToOne
    private Post post;

    @JsonManagedReference
    @OneToOne(mappedBy = "diary", cascade = CascadeType.ALL)
    private List<Photo> photos;

}
