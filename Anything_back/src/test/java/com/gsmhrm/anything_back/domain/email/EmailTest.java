package com.gsmhrm.anything_back.domain.email;

import com.gsmhrm.anything_back.domain.email.entity.Email;
import com.gsmhrm.anything_back.domain.email.exception.MisMatchAuthKeyException;
import com.gsmhrm.anything_back.domain.email.presentation.dto.request.EmailCheckDto;
import com.gsmhrm.anything_back.domain.email.presentation.dto.request.EmailSendRequest;
import com.gsmhrm.anything_back.domain.email.repository.EmailRepository;
import com.gsmhrm.anything_back.domain.email.service.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;

    @Test
    @DisplayName("이메일 전송 테스트")
    public void testExecute_SendEmail() {
        EmailSendRequest request = new EmailSendRequest("zadzed1100@gmail.com");

        //When
        emailService.execute(request);

        //Given
        Email savedEmail = emailRepository.findById("zadzed1100@gmail.com")
                        .orElseThrow(RuntimeException::new);

        assertThat(savedEmail.getEmail()).isEqualTo(request.getEmail());
        assertThat(savedEmail.getRandomValue()).isNotEmpty();
        assertThat(savedEmail.getAuthentication()).isFalse();
        assertThat(savedEmail.getAttemptCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("이메일 인증 테스트")
    public void testExecute_AuthenticateEmail() {
        // Given
        String email = "test@example.com";
        String authKey = "1234";
        Email emailEntity = new Email(email, authKey, false, 0);
        emailRepository.save(emailEntity);

        // When
        EmailCheckDto emailCheckDto = new EmailCheckDto(email, authKey);
        emailService.execute(emailCheckDto);

        // Then
        Email authenticatedEmail = emailRepository.findById(email).orElse(null);
        assertThat(authenticatedEmail).isNotNull();
        assertThat(authenticatedEmail.getAuthentication()).isTrue();
    }

    @Test
    @DisplayName("이메일 오류 테스트")
    public void testExecute_MismatchAuthKey() {
        // Given
        Email emailEntity = new Email("test@example.com", "1234", false, 0);
        emailRepository.save(emailEntity);
        EmailCheckDto emailCheckDto = new EmailCheckDto("test@example.com", "5678");

        // When

        // Then
        assertThatThrownBy(() -> emailService.execute(emailCheckDto))
                .isInstanceOf(MisMatchAuthKeyException.class);
    }
}
