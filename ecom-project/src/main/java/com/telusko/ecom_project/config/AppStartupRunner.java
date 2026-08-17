package com.telusko.ecom_project.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppStartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Application Started Successfully ===");
        System.out.println("Command-line arguments count: " + args.length);

        if (args.length > 0) {
            System.out.println("Command-line arguments list:");
            Arrays.stream(args).forEach(arg -> System.out.println("- " + arg));
        } else {
            System.out.println("No command-line arguments were provided.");
        }
    }
}