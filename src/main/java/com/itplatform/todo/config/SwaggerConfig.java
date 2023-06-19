package com.itplatform.todo.config;


import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("todo-task API 문서")
                .description("todo-task의 api 문서입니다.")
                .version("1.0.0");
    }
}