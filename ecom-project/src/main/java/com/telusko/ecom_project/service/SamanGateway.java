package com.telusko.ecom_project.service;

import org.springframework.stereotype.Service;

@Service
public class SamanGateway implements PaymentGateway{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via Saman Bank...");
        return true;
    }
}
