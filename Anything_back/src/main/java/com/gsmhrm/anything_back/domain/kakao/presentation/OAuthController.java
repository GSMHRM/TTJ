package com.gsmhrm.anything_back.domain.kakao.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gsmhrm.anything_back.domain.kakao.presentation.dto.KakaoUserInfo;
import com.gsmhrm.anything_back.domain.kakao.service.KakaoUserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final KakaoUserService kakaoUserService;

    @RequestMapping("/oauth2/kakao")
    @ResponseBody
    @GetMapping
    public KakaoUserInfo handleKakao(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        return kakaoUserService.kakaoLogin(code, response);
    }
}
