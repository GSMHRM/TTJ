package com.gsmhrm.anything_back.domain.kakao.presentation;

import com.gsmhrm.anything_back.domain.kakao.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService service;

    @RequestMapping("/oauth2/kakao")
    @ResponseBody
    @GetMapping
    public ResponseEntity<?> handleKakao(@RequestParam String code) {
        String access_Token = service.getKakaoAccessToken(code);
        service.createKakaoUser(access_Token);
        System.out.println("access_Token = " + access_Token);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/test"));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
}
