package com.telusko.ecom_project.controller;

import com.telusko.ecom_project.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/buy")
    public ResponseEntity<String> buyProduct(@RequestParam int productId , @RequestParam int quantity){
        try {
            String result = checkoutService.processOrder(productId, quantity);
            if (result.startsWith("Order placed successfully!")) {
                return new ResponseEntity<>(result,HttpStatus.OK);
            } else {
                return new  ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
            }
        }catch (RuntimeException e){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/test-scope")
    public ResponseEntity<String> testScope() {
        String result = checkoutService.checkTaskTrackerScope();
        return ResponseEntity.ok(result);
    }
}
