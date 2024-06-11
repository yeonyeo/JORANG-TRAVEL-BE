package com.example.travel_diary.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "CREATED_AT")
    @Setter
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Diary> diaries;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;
}
