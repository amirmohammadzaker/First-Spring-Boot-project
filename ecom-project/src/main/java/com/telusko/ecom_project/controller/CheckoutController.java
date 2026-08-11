package com.telusko.ecom_project.controller;

import com.telusko.ecom_project.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/buy")
    public ResponseEntity<String> buyProduct(@RequestParam int productId , @RequestParam int quantity){
        try {
            String result = checkoutService.processOrder(productId, quantity);
            if ("Order placed successfully!".equals(result)) {
                return new ResponseEntity<>(result,HttpStatus.OK);
            } else {
                return new  ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
            }
        }catch (RuntimeException e){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
