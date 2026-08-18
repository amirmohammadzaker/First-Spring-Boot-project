package com.telusko.ecom_project.controller;

import com.telusko.ecom_project.service.HeavyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HeavyController {

    @Lazy
    private final HeavyService heavyService;

    @GetMapping("/heavy")
    public String triggerHeavyService() {
        return heavyService.process();
    }
}