package com.project.bookstore.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> openApi.info(new Info()
                .title("Bookstore Catalog")
                .description("This API allows you to manage books and authors in a bookstore system.")
                .version("1.0.0")
                .contact(new Contact()
                        .name("John")
                        .email("rsjohn232@gmail.com")));
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));

    }
}
