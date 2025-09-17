package com.api.oficina;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackages = {"com.api.oficina.model"})
@ComponentScan(basePackages = {"com.api.*"}) //Realizar injeção de dependências.
@EnableJpaRepositories(basePackages = {"com.api.oficina.repository"}) // Habilita a funcionalidade das interfaces de persistência.
@EnableTransactionManagement// Gerenciador de transações
@EnableWebMvc // Ativa o projeto de MVC
@RestController
@EnableAsync
/////@EnableAutoConfiguration // Configura todo o projeto (manipulações de classes, dependencias...)
public class ApiOficinaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiOficinaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {
			System.out.println("Sistema Inicado");
		};
	}

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
		System.out.println("Time zone set to Europe/London");
	}
	
}
