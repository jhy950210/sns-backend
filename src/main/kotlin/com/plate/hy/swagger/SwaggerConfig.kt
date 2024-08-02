package com.plate.hy.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    private final val SECURITY_SCHEME_NAME = "authorization"

    @Bean
    fun swaggerApi(): OpenAPI = OpenAPI()
        .components(Components()
        .addSecuritySchemes(SECURITY_SCHEME_NAME, SecurityScheme()
            .name(SECURITY_SCHEME_NAME)
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")))
        .addSecurityItem(SecurityRequirement().addList(SECURITY_SCHEME_NAME))
        .info(
            Info()
            .title("HY base project")
            .version("1.0.0"))
}
