package com.telusko.ecom_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientInfoController {

    @GetMapping("/info")
    public ResponseEntity<String> getClientInfo(@RequestHeader(value = "User-Agent", defaultValue = "Unknown") String userAgent) {
        System.out.println("==========================================");
        System.out.println("Client User-Agent: " + userAgent);
        System.out.println("==========================================");

        return ResponseEntity.ok("User-Agent received: " + userAgent);
    }
}