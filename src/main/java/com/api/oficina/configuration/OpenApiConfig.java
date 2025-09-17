package com.api.oficina.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenApiConfig {
	
	
	public Contact contact() {
		Contact contact = new Contact();
		contact.setName("Anthony");
		contact.setEmail("anthony@gmail.com");
		contact.setUrl("www.x.com");
		return contact;
	}
	
	public Info info() {
		Info info = new Info();
		info.setContact(this.contact());
		info.setTitle("Gerenciamento de Oficinas");
		return info;
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI()
				.info(this.info());
		
	}
	
	@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/oficina/**")
                .packagesToScan("com.api.oficina.controller")
                .build();
    }
}
