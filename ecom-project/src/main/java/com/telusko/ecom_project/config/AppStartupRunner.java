package com.telusko.ecom_project.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=== Application Started with ApplicationRunner ===");

        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("\n[Non-Option Arguments]: " + nonOptionArgs.size());
        nonOptionArgs.forEach(arg -> System.out.println("- " + arg));

        Set<String> optionNames = args.getOptionNames();
        System.out.println("\n[Option Arguments]: " + optionNames.size());
        for (String name : optionNames) {
            List<String> values = args.getOptionValues(name);
            System.out.println("- Key: " + name + " | Value(s): " + values);
        }
    }
}