package com.gsmhrm.anything_back.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsmhrm.anything_back.global.exception.AnythingException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import com.gsmhrm.anything_back.global.exception.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.gsmhrm.anything_back.global.exception.ErrorCode.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    public void setErrorResponse(ErrorCode errorCode, HttpServletResponse response) throws IOException {
        response.setStatus(errorCode.getStatus());
        response.setContentType("application/json; charset=utf-8");

        ErrorMessage errorMessage = new ErrorMessage(errorCode);

        String errorResponseEntityToJson = objectMapper.writeValueAsString(errorMessage);
        response.getWriter().write(errorResponseEntityToJson);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 ExpiredJwtException 발생 ===================");
            setErrorResponse(TOKEN_IS_EXPIRED, response);
        } catch (JwtException | AnythingException ex) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 TokenNotValidException 발생 ===================");
            setErrorResponse(TOKEN_NOT_VALID, response);
        } catch (Exception ex) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 Exception 발생 ===================");
            setErrorResponse(UNKNOWN_ERROR, response);
        }
    }
}