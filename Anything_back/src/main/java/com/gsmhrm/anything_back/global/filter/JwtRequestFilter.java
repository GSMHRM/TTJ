package com.gsmhrm.anything_back.global.filter;

import com.gsmhrm.anything_back.global.security.exception.TokenNotValidException;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.security.jwt.properties.JwtProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private final Logger log = (Logger) LoggerFactory.getLogger(JwtRequestFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = tokenProvider.resolveToken(request);

        if (token != null && !token.isBlank()) {

            Authentication authentication = tokenProvider.authenticationToken(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("current Email = " + authentication.getName());
        }
        
        filterChain.doFilter(request, response);
    }
}