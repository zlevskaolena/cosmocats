package com.cosmocats.intergalacticmarketplace.entity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Cosmo Cats Intergalactic Marketplace API",
                version = "1.0",
                description = "API для управління космічними товарами"
        ),
        servers = @Server(url = "/api", description = "API server") // змінили на /api
)
public class SwaggerConfig {
}
