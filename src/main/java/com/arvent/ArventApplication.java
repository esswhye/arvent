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

//cd\JavaProjects\SY_Github\arvent\target
//java -jar arvent-0.0.1-SNAPSHOT.jar
//curl -i -X POST http://localhost:8080/actuator/shutdown