package com.example.refmonolithicserver.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("REF-API Definition")
                .version("1.0")
                .description("ref api-docs");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
