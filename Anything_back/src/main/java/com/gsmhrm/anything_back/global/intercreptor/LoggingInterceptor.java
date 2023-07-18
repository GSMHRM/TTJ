package com.gsmhrm.anything_back.global.intercreptor;

import com.gsmhrm.anything_back.global.config.web.PrettyConverter;
import com.gsmhrm.anything_back.global.wrapper.MultiAccessRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;


@Component
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {
    private PrettyConverter converter; // NormalConverter, PrettyConverter를 갈아끼우기만 하면 됩니다.

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // form-data를 담는 request 객체는 타입 캐스팅을 할 수 없어서 처리해줍니다.
        if (!request.getContentType().startsWith("multipart/form-data")) {
            MultiAccessRequestWrapper wrapRequest = (MultiAccessRequestWrapper) request;
            byte[] contents = wrapRequest.getContents();
            String body = converter.convert(contents);
            log.info(
                    "-------------> [REQUEST] {} {} {} BODY\n{}",
                    request.getRemoteAddr(),
                    request.getMethod(),
                    request.getRequestURL(),
                    body
            );
        } else {
            log.info(
                    "-------------> [REQUEST] {} {} {}",
                    request.getRemoteAddr(),
                    request.getMethod(),
                    request.getRequestURL()
            );
        }
        return true;
    }

    // 이래야 핸들러에서 예외가 발생해도 수행 됨
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContentCachingResponseWrapper wrapResponse = (ContentCachingResponseWrapper) response;
        byte[] bodyBytes = wrapResponse.getContentAsByteArray();
        String body = converter.convert(bodyBytes);
        log.info("<------------ [RESPONSE] {} JSON {}", response.getStatus(), body);
    }
}

