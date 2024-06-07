package com.example.travel_diary.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "USER_ID")
    private UUID id;
    @Column(name = "USER_LOGIN_ID")
    private String loginId;
    @Column(name = "USER_NAME")
    private String name;
    @Column(name = "USER_NICKNAME") @Setter
    private String nickname; @Setter
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "USER_EMAIL")
    private String email;
    @Column(name = "USER_CREATED_AT")
    private LocalDateTime createdAt;


}
