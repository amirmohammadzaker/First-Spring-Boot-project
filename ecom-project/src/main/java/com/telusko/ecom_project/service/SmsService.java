package com.telusko.ecom_project.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "sms",
    value = "enabled",
    havingValue = "true",
    matchIfMissing = false)
public class SmsService {
    public SmsService(){
        System.out.println("Bean of SmsService created");
    }
}
