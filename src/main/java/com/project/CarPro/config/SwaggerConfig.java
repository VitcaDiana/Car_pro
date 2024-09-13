package com.project.CarPro.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SwaggerConfig {
    @Bean
    @Primary
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "OAuth2";
        return new OpenAPI()
                .info(new Info().title("Sample API").version("v1"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl("http://localhost:8070/realms/master/protocol/openid-connect/auth")
                                                .tokenUrl("http://localhost:8070/realms/master/protocol/openid-connect/token")
                                                .scopes(new Scopes().addString("read", "read access").addString("write", "write access"))
                                        )
                                )
                        )
                );
    }

}
