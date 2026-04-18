package com.exerciciogpt2.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Mentorias")
                        .description("Sistema de gerenciamento de mentorias")
                        .version("1.0"));
    }

    @Bean
    public CommandLineRunner openSwagger() {
        return args -> {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:8080/swagger-ui/index.html"));
            }
        };
    }
}
