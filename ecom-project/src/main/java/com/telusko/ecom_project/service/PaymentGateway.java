package com.telusko.ecom_project.service;

public interface PaymentGateway {
    public boolean processPayment(double amount);
}
