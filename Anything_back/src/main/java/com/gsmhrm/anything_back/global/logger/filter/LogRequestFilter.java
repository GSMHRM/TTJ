package com.gsmhrm.anything_back.global.logger.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LogRequestFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("client ip = {}", request.getRemoteAddr());

        log.info("request method = {}", request.getMethod());

        log.info("request url = {}", request.getRequestURI());

        log.info("client info = {}", request.getHeader("User-Agent"));

        filterChain.doFilter(request, response);

        log.info("response status = {}", response.getStatus());
    }
}
