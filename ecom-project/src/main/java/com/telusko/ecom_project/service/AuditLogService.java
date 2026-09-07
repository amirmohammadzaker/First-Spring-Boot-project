package com.telusko.ecom_project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditLogService {

    // This method runs in a completely new and independent transaction
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logOrderAttempt(String details) {
        // 1. Perform a separate database operation (e.g., saving into the audit_logs table)
        System.out.println("Logging in a separate transaction: " + details);

        // 2. Throw a fake exception to test whether only this transaction gets rolled back or not
        throw new RuntimeException("Separate transaction error (AuditLog)!");
    }
}