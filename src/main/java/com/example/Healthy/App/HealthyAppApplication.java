package com.example.Healthy.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example.Healthy.App")
@EnableJpaRepositories("com.example.Healthy.App.repository")
public class HealthyAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(HealthyAppApplication.class, args);
	}

}
