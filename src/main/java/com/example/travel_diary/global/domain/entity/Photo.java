package com.example.travel_diary.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PHOTOS")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHOTO_ID")
    private Long id;

    @Column(name = "PATH")
    @Setter
    private String path;

    @JoinColumn(name = "DIARY_ID")
    @ManyToOne
    private Diary diary;
}
