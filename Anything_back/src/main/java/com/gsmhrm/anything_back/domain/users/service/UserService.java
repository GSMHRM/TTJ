package com.gsmhrm.anything_back.domain.users.service;

import com.gsmhrm.anything_back.domain.users.entity.User;
import com.gsmhrm.anything_back.domain.users.exception.EmailNotFoundException;
import com.gsmhrm.anything_back.domain.users.exception.UserEmailException;
import com.gsmhrm.anything_back.domain.users.presentation.dto.SignInRequest;
import com.gsmhrm.anything_back.domain.users.presentation.dto.SignUpRequest;
import com.gsmhrm.anything_back.domain.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpRequest signupRequest) throws Exception {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new UserEmailException("이메일이 이미 있는 이메일임");
        }

        User user = User.builder()
                .email(signupRequest.getEmail())
                .name(signupRequest.getName())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(signupRequest.getRole())
                .build();
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public String login(SignInRequest signinRequest) {
        User user = userRepository
                .findUserByEmail(signinRequest.getEmail())
                .orElseThrow(()->new EmailNotFoundException("이메일을 찾지 못했습니다"));

        return "token";
    }
}
