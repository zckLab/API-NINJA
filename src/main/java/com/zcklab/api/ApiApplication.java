package com.zcklab.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApiApplication.class);

		// Isso aqui ignora o erro do seu arquivo e liga o H2 na marra
		app.setDefaultProperties(java.util.Map.of(
				"spring.h2.console.enabled", "true",
				"server.port", "8081"
		));

		app.run(args);
	}
}
