package com.gsmhrm.anything_back.domain.email.service;

import com.gsmhrm.anything_back.domain.email.entity.Email;
import com.gsmhrm.anything_back.domain.email.presentation.dto.EmailSendRequest;
import com.gsmhrm.anything_back.domain.email.repository.EmailRepository;
import com.gsmhrm.anything_back.global.annotation.ReadOnlyService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Random;

@ReadOnlyService
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    public void execute(String email, String authKey) {

        Email emailAuth = emailRepository.findById(email).orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없음"));
        checkAuthKey(emailAuth, authKey);
        emailAuth.updateAuthentication(true);
        emailRepository.save(emailAuth);
    }

    private void checkAuthKey(Email email, String authKey) {
        if (!Objects.equals(email.getRandomValue(), authKey))
            throw new RuntimeException("인증번호가 일치 하지 않음");
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(EmailSendRequest sendRequest) {
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(8888) + 1111);

        sendAuthEmail(sendRequest.getEmail(), authKey);
    }

    private void sendAuthEmail(String email, String authKey) {

        String title = "HRM 인증번호";
        String content = "회원 가입을 위한 인증번호는" + authKey + " 입니다 <br />.";

        Email emailAuth = Email.builder()
                .email(email)
                .randomValue(authKey)
                .attemptCount(0)
                .authentication(false)
                .build();

        if(emailAuth.getAttemptCount() >= 3) {
            throw new RuntimeException("이메일은 최대 3회 까지 요청이 가능합니다.");
        }
        emailAuth.updateRandomValue(authKey);
        emailAuth.increaseAttemptCount();

        emailRepository.save(emailAuth);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            messageHelper.setTo(email);
            messageHelper.setSubject(title);
            messageHelper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일을 보내는데 실패했습니다.");
        }
    }
}
