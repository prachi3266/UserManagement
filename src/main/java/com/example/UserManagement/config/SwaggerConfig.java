package com.example.UserManagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@OpenAPIDefinition(
//        security = @SecurityRequirement(name = "basicAuth")
//)
//@SecurityScheme(
//        name = "basicAuth",
//        type = SecuritySchemeType.HTTP,
//        scheme = "basic"
//)

@OpenAPIDefinition(security = @SecurityRequirement(name= "bearerAuth"))
@SecurityScheme(name= "bearerAuth",
        type= SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI userManagementAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("User Management API")
                            .description("Spring Boot REST API for User Management")
                            .version("1.0"));
        }


}
