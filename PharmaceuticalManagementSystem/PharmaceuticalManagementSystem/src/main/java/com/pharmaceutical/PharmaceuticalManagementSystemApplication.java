package com.pharmaceutical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main class for the Pharmaceutical Management System application.
 * It serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
public class PharmaceuticalManagementSystemApplication {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(PharmaceuticalManagementSystemApplication.class, args);
    }
}
