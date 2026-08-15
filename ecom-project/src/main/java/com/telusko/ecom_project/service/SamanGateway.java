package com.telusko.ecom_project.service;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SamanGateway implements PaymentGateway{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via Saman Bank...");
        return true;
    }
    @PreDestroy
    public void cleanup() {
        System.out.println("=============================================");
        System.out.println("Closing connection to Saman Payment Service...");
        System.out.println("=============================================");
    }
}
