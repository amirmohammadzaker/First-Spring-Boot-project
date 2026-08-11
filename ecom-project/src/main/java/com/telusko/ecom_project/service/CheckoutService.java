package com.telusko.ecom_project.service;

import com.telusko.ecom_project.model.Product;
import com.telusko.ecom_project.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final ProductRepo productRepo;

    @Qualifier("samanGateway")
    private final PaymentGateway paymentGateway;

    @Transactional
    public String processOrder(int productId, int quantity) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < quantity) {
            return "Stock not sufficient!";
        }

        double totalAmount = product.getPrice().doubleValue() * quantity;
        boolean paymentSuccess = paymentGateway.processPayment(totalAmount);

        if (paymentSuccess) {
            product.setStockQuantity(product.getStockQuantity() - quantity);

            if (product.getStockQuantity() == 0) {
                product.setProductAvailable(false);
            }

            productRepo.save(product);
            return "Order placed successfully!";
        } else {
            return "Payment failed!";
        }
    }
}