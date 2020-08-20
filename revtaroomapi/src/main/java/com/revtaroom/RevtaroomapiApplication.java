package com.revtaroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.revtaroom.repositories")
public class RevtaroomapiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RevtaroomapiApplication.class, args);
	}

}
