package com.attendease.attendease;

import com.attendease.attendease._core.springjdbc.AppDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AttendeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendeaseApplication.class, args);

		AppDatabase appDatabase = AppDatabase.getInstance();
	}

}
