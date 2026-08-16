package com.telusko.ecom_project.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.jpa.db")
@Data
public class DbSettings {
    private String connection;
    private String host;
    private int port;
}
