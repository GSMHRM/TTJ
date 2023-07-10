package com.gsmhrm.anything_back.domain.email.presentation;

import com.gsmhrm.anything_back.domain.email.presentation.dto.request.EmailCheckDto;
import com.gsmhrm.anything_back.domain.email.presentation.dto.request.EmailSendRequest;
import com.gsmhrm.anything_back.domain.email.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody @Valid EmailSendRequest sendRequest) {
        emailService.execute(sendRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/email/auth", method = RequestMethod.POST)
    public ResponseEntity<Void> authEmail(@RequestBody @Valid EmailCheckDto emailCheckDto){
        emailService.execute(emailCheckDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
