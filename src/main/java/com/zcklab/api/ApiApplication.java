package com.zcklab.api;

import com.zcklab.api.Enums.Category;
import com.zcklab.api.Enums.Elementals;
import com.zcklab.api.Enums.Rank;
import com.zcklab.api.Model.Ninja;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;


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
