package com.gsmhrm.anything_back.global.security.config;

import com.gsmhrm.anything_back.global.filter.ExceptionFilter;
import com.gsmhrm.anything_back.global.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final ExceptionFilter exceptionFilter;


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //DSL 사용 보안구성
        http
                .httpBasic().disable() //UI, UX Disable
                .csrf().disable()//크로스 사이트 기능
                .cors().and() //도메인이 다를때 허용
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                //인가 정책
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/email/**").permitAll()
                .anyRequest().denyAll();
        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
