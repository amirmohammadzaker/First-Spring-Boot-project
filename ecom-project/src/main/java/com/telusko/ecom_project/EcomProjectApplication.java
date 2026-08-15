package com.telusko.ecom_project;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcomProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomProjectApplication.class, args);
	}
	@PostConstruct
	public void init() {
		System.out.println("==========================================");
		System.out.println(" Welcome! Application Context Initialized! ");
		System.out.println("==========================================");
	}
}
