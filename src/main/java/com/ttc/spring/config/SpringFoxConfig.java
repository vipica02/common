package com.ttc.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
public class SpringFoxConfig {
    @Value("${base-path:#{null}}")
    String basePath;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build().apiInfo(getApiInfo())
                .pathMapping(basePath);
    }
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Dịch vụ Momo service",
                "Dịch vụ Momo",
                "1.0.0",
                "https://ttc.com.vn",
                new Contact("0942314259", "https://ttc.com.vn",
                        "https://lienvietpostbank.com.vn"),
                "",
                "https://ttc.com.vn",
                Collections.emptyList()
        );
    }
}
