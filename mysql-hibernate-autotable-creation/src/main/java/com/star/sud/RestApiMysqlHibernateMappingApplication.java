package com.star.sud;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiMysqlHibernateMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiMysqlHibernateMappingApplication.class, args);
	}

	@PostConstruct
	public void postMasterData() {

		System.out.println("Hiii");

	}

}
