package com.telusko.ecom_project.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestValue {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${Test : default }")
    private String test;
    @PostConstruct
    public void init() {
        System.out.println(this.applicationName + " " + this.test);
    }
}
