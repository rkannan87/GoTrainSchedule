package com.rbc.tech.gotrain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI schdeuleMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("GO Train Schedule API")
                        .description("API provides a (simplified) train timetable with weekday train times leaving Union Station.")
                        .version("1.0"));
    }
}
