package com.gsmhrm.anything_back.domain.email.presentation;

import com.gsmhrm.anything_back.domain.email.presentation.dto.EmailSendRequest;
import com.gsmhrm.anything_back.domain.email.service.EmailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> authEmail(@Email @RequestParam String email, @RequestParam String authKey){
        emailService.execute(email, authKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
