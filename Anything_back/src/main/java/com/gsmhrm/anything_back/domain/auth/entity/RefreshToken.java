package com.gsmhrm.anything_back.domain.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash(value = "refeshToken")
public class RefreshToken {

    private String email;

    @Id
    private String refreshToken;

    @TimeToLive
    private Long expiredAt;

    public void exchangeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
