package com.gsmhrm.anything_back.global.security.config;

import com.gsmhrm.anything_back.global.filter.ExceptionFilter;
import com.gsmhrm.anything_back.global.filter.JwtRequestFilter;
import com.gsmhrm.anything_back.global.logger.filter.CustomServletWrapperFilter;
import com.gsmhrm.anything_back.global.logger.filter.LogRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final ExceptionFilter exceptionFilter;
    private final LogRequestFilter logRequestFilter;
    private final CustomServletWrapperFilter customServletWrapperFilter;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //DSL 사용 보안구성
        http
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
        http
                .rememberMe().disable()
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable();
        http
                .httpBasic().disable() //UI, UX Disable
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                //인가 정책
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/email/**").permitAll()
                .requestMatchers("/plan/**", "/plan").authenticated()
                .requestMatchers("/user/**", "/user").authenticated()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/info/**").authenticated()
                .requestMatchers("/oauth2/**").permitAll()
                .anyRequest().denyAll();
        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionFilter, JwtRequestFilter.class)
                .addFilterBefore(logRequestFilter, ExceptionFilter.class)
                .addFilterBefore(customServletWrapperFilter, ExceptionFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
