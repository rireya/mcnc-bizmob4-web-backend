package com.mcnc.bizmob.web.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(url = "https://192.168.3.35:8281/admin", description = "[DEV] hiplus admin https server url"),
                @Server(url = "http://localhost:8281/admin", description = "[LOCAL] hiplus admin http")
        }
)
public class SwaggerConfig {
	@Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }
 
    private Info apiInfo() {
        return new Info()
                .title("SM 하이플러스 - 관리자")
                .description("Spring boot 2.7.18 + java 8 + maven + swagger 2 + bizmob client")
                .version("1.0.0")
                .contact(new Contact().name("mobile C&C").email("sh_kim@mcnc.co.kr"));
    }
}
