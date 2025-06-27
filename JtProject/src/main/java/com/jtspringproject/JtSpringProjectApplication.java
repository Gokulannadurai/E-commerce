package com.jtspringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main application entry point for the Spring Boot application.
 */
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableCaching
@EnableAsync
public class JtSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JtSpringProjectApplication.class, args);
	}

} 