package com.telusko.ecom_project;

import com.telusko.ecom_project.ignore.Ignore;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
		basePackages = "com.telusko.ecom_project",
		excludeFilters = @ComponentScan.Filter(
				type = FilterType.ASSIGNABLE_TYPE,
				classes = Ignore.class
		)
)
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
