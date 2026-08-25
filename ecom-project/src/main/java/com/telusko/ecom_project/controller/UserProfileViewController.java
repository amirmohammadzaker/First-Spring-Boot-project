package com.telusko.ecom_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserProfileViewController {

    @GetMapping("/")
    public String getUserProfile(@RequestParam(defaultValue = "کاربر عزیز") String name, Model model) {
        model.addAttribute("userName", name);
        return "user-profile";
    }
}