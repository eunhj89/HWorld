package com.uno.hworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HworldApplication.class, args);
	}

}
