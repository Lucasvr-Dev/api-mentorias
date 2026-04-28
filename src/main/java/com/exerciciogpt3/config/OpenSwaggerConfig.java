package com.exerciciogpt3.config;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenSwaggerConfig {

    @Bean
    public CommandLineRunner openSwagger() {
        return args -> {
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:8080/swagger-ui/index.html"));
            } catch (Exception e) {
                System.out.println("Não foi possível abrir o navegador automaticamente.");
            }
        };
    }
}