package com.gsmhrm.anything_back.global.filter;

import com.gsmhrm.anything_back.global.security.exception.TokenNotValidException;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.security.jwt.properties.JwtProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    public void registerSecurityContext(HttpServletRequest request, String email) {
        UsernamePasswordAuthenticationToken authenticationToken = tokenProvider.authenticationToken(email);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String accessToken = request.getHeader("Authorization");

        if (!Objects.isNull(accessToken)) {
            tokenProvider.extractAllClaims(accessToken, jwtProperties.getAccessSecret());

            if (!tokenProvider.getTokenType(accessToken, jwtProperties.getAccessSecret()).equals("ACCESS_TOKEN")) {
                throw new TokenNotValidException();
            }
            String email = tokenProvider.getUserEmail(accessToken, jwtProperties.getAccessSecret());
            registerSecurityContext(request, email);
        }
        filterChain.doFilter(request, response);
    }
}
