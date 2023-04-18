package com.gsmhrm.anything_back.domain.users.entity;

import com.gsmhrm.anything_back.domain.users.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public void updatePassword(String password) {
        this.password = password;
    }
}
