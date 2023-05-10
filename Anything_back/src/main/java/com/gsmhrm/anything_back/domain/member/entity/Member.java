package com.gsmhrm.anything_back.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

    @Column(nullable = false, name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
