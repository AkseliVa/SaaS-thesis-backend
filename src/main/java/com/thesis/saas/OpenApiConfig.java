package com.thesis.saas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI saasOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SaaS REST API")
                        .description("SaaS REST API endpoints")
                        .version("1.0"));
    }
}