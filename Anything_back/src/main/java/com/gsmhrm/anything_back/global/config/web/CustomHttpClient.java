package com.gsmhrm.anything_back.global.config.web;

import lombok.RequiredArgsConstructor;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@RequiredArgsConstructor
public class CustomHttpClient {

    private final HttpClient httpClient;

    public CustomHttpClient() {
        httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
        httpClient.getParams().setParameter("http.protocol.content-charset",
                "UTF-8");
        httpClient.getHttpConnectionManager().getParams()
                .setConnectionTimeout(60000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        httpClient.setHostConfiguration(new HostConfiguration());
    }

    @Bean
    public HttpClient getHttpClient() {
        return httpClient;
    }
}
