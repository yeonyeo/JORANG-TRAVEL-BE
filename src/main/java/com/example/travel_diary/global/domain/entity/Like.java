package com.example.travel_diary.global.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LIKES")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    Long id;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    User user;

    @JoinColumn(name = "POST_ID")
    @ManyToOne
    Post post;
}
