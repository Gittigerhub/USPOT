package com.example.uspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UspotApplication {

	public static void main(String[] args) {
		SpringApplication.run(UspotApplication.class, args);
	}

}
