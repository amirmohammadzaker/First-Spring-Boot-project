package com.telusko.ecom_project.service;

public class MellatGateway implements PaymentGateway{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via Mellat Bank...");
        return true;
    }
}
