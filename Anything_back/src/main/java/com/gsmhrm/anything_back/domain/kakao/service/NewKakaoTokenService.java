package com.gsmhrm.anything_back.domain.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsmhrm.anything_back.domain.auth.entity.KakaoAuth;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RollbackService
@RequiredArgsConstructor
public class NewKakaoTokenService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;

    public String[] execute(KakaoAuth auth) throws JsonProcessingException {
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 본문 데이터 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("client_id", CLIENT_ID);
        body.add("refresh_token", auth.getRefreshToken());

        // 헤더와 본문 데이터를 하나의 요청 객체로 설정
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                entity,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String accessToken = jsonNode.get("access_token").asText();
        String refreshToken = jsonNode.get("refresh_token").asText();
        String expires_in = jsonNode.get("expires_in").asText();
        String refresh_token_expires_in = "";

        if (refreshToken == null) {
            return new String[] {accessToken, expires_in};
        } else {
            refresh_token_expires_in = jsonNode.get("refresh_token_expires_in").asText();
        }

        return new String[] {accessToken, expires_in, refreshToken, refresh_token_expires_in};
    }
}
