package com.arvent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ArventApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArventApplication.class, args);
	}

}
