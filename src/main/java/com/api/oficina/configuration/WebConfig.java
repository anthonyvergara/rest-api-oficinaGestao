package com.api.oficina.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	@Value("${cliente.origin.www}")
    private String cliente_origin_www;

    @Value("${cliente.origin.sem.www}")
    private String cliente_origin_sem_www;

	@Override
    public void addCorsMappings(CorsRegistry registry) {
		logger.info("Cliente Origin www: {}", cliente_origin_www);
        logger.info("Cliente Origin sem www: {}", cliente_origin_sem_www);

        registry.addMapping("/**")
                .allowedOrigins(cliente_origin_www, cliente_origin_sem_www)  // Usando a variável de ambiente
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
	
	
	

}
