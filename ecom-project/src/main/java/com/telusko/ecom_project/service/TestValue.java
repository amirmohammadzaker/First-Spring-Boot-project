package com.telusko.ecom_project.service;

import com.telusko.ecom_project.config.DbSettings;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestValue {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${Test : default }")
    private String test;

    private final DbSettings dbSettings;
    @PostConstruct
    public void init() {
        System.out.println(this.applicationName + " " + this.test);
        System.out.println(dbSettings.getConnection());
        System.out.println(dbSettings.getPort());
        System.out.println(dbSettings.getHost());
    }
}
