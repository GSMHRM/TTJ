package com.gsmhrm.anything_back.global.security.jwt.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class TokenTimeProperties {

    private Long accessTime;

    private Long refreshTime;
}
