package com.gsmhrm.anything_back.domain.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash(value = "kakaoAuth")
public class KakaoAuth {

    private String email;

    private String accessToken;

    private String refreshToken;

    public void changeAccessToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
