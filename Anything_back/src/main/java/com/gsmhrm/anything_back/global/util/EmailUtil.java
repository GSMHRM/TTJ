package com.gsmhrm.anything_back.global.util;

import com.gsmhrm.anything_back.domain.email.entity.Email;
import com.gsmhrm.anything_back.domain.email.exception.ExistEmailException;
import com.gsmhrm.anything_back.domain.email.exception.NotAuthenticationEmailException;
import com.gsmhrm.anything_back.domain.email.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUtil {

    private final EmailRepository emailRepository;

    public Email getEmail(String email) {
        return emailRepository.findById(email)
                .orElseThrow(ExistEmailException::new);
    }

    public void checkEmailAuthentication(Email emailAuth) {
        if(!emailAuth.getAuthentication()) {
            throw new NotAuthenticationEmailException();
        }
    }
}