package com.telusko.ecom_project.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/version")
public class AppVersionController {

    @GetMapping
    public ResponseEntity<String> getAppVersion() {
        return ResponseEntity.ok()
                .header("X-App-Version", "1.0.0")
                .body("اطلاعات نسخه برنامه");
    }
}