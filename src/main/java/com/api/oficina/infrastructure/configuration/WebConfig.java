package com.api.oficina.infrastructure.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	@Value("${cliente.origin}")
    private String clienteOrigin;
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		logger.info("Cliente Origin: {}", clienteOrigin);  
		
        registry.addMapping("/**")
                .allowedOrigins(clienteOrigin)  // Usando a variável de ambiente
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
	
	
	

}
