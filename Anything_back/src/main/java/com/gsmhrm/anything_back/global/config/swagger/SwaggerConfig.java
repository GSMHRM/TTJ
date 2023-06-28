package com.gsmhrm.anything_back.global.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true) // Swagger 에서 제공해주는 기본 응답 코드 (200, 401, 403, 404) 등의 노출 여부
                .apiInfo(apiInfo()) // Swagger   UI 로 노출할 정보
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()) // apis 에 위치하는 API 중 특정 path 를 선택
                .build();
    }

    public ApiInfo apiInfo() {
        return new  ApiInfoBuilder()
                .title("TTJ API Documentation")
                .description("TTJ API 명세서")
                .contact(new Contact("[TTJ]", "https://github.com/GSMHRM/TTJ-Backend", "rubycpp1225@outlook.kr"))
                .version("0.1")
                .build();
    }
}
