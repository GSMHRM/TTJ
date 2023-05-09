package com.gsmhrm.anything_back.domain.auth.presentation.dto.request;

import com.gsmhrm.anything_back.domain.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@Component
@NoArgsConstructor
public class SignUpRequest {

    private String email;
    private String name;
    private String password;
    private Role role;
}
