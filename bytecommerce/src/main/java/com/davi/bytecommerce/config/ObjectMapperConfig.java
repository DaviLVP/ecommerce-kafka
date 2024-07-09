package com.davi.bytecommerce.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class ObjectMapperConfig {
    @Bean
    private ObjectMapper objectMapperConfig() {
        return new ObjectMapper();
    }
}
