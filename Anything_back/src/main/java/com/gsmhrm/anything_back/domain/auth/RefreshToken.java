package com.gsmhrm.anything_back.domain.auth;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private String email;

    private String refreshToken;

    @TimeToLive
    private long expiredAt;

    public void exchangeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}