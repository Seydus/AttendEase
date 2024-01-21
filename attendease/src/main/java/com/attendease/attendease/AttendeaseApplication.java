package com.attendease.attendease;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.attendease.attendease")
@ComponentScan({"com.attendease.attendease", "com.attendease.attendease.core.repository"})
public class AttendeaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(AttendeaseApplication.class, args);
	}
}
