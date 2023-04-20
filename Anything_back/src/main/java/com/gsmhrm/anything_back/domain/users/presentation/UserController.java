package com.gsmhrm.anything_back.domain.users.presentation;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.LoginResponse;
import com.gsmhrm.anything_back.domain.users.presentation.dto.SignInRequest;
import com.gsmhrm.anything_back.domain.users.presentation.dto.SignUpRequest;
import com.gsmhrm.anything_back.domain.users.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signupRequest) throws Exception {
        userService.signUp(signupRequest);
        return ResponseEntity.ok().body("회원가입이 성공했습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid SignInRequest loginRequest) {
        LoginResponse loginResponse = userService.execute(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
