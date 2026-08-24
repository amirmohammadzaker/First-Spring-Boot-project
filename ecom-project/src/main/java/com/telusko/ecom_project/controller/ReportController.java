package com.telusko.ecom_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://trusted-site.com:5173")
@RequestMapping("/api/reports")
public class ReportController {

    @GetMapping
    public ResponseEntity<String> getReports() {
        return new ResponseEntity<>("اطلاعات گزارش‌ها ارسال شد", HttpStatus.OK);
    }
}