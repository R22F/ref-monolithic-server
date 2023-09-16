package com.example.refmonolithicserver.common.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public record AppProperties() {

    @Data
    @Component
    @ConfigurationProperties(prefix = "app.jwt")
    public static class Jwt {
        private String SECRET;
        private int EXPIRATION_TIME;
        private String TOKEN_PREFIX;
        private String HEADER_STRING;
    }
}