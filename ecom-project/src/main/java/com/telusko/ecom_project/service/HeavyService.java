package com.telusko.ecom_project.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class HeavyService {

    public HeavyService() {
        System.out.println("Starting HeavyService initialization...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("HeavyService initialized successfully!");
    }

    public String process() {
        return "Heavy service task executed!";
    }
}