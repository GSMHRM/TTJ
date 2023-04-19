package com.gsmhrm.anything_back.global.security.jwt;

import com.gsmhrm.anything_back.global.security.auth.MemberDetailsService;
import com.gsmhrm.anything_back.global.security.jwt.properties.JwtProperties;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Getter
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final MemberDetailsService memberDetailsService;
    private final JwtProperties jwtProperties;
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 120;
    private final long REFRESH_TOKEN_EXPIRE_TIME = ACCESS_TOKEN_EXPIRE_TIME * 12 * 7;

    @AllArgsConstructor
    private enum TokenType {
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken");
        String value;
    }

    @AllArgsConstructor
    private enum TokenClaimName {
        USER_EMAIL("email"),
        TOKEN_TYPE("tokenType");
        String value;
    }

    private Key getSignkey(String secretKey) {
        byte[] bytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

}
