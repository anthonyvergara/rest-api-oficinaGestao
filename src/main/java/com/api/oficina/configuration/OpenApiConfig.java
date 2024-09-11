package com.api.oficina.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
	
	@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/oficina/**", "/cliente/**","/telefone/**","/documento/**","/dono/**","/endereco/**","/ordemServico/**")
                .packagesToScan("com.api.oficina.controller")
                .build();
    }
}
