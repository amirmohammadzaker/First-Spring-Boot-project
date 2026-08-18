package com.telusko.ecom_project.service;

import com.telusko.ecom_project.model.Product;
import com.telusko.ecom_project.repo.ProductRepo;
import com.telusko.ecom_project.service.discount.DefaultDiscountStrategy;
import com.telusko.ecom_project.service.discount.DiscountStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final ProductRepo productRepo;
    private final PaymentGateway paymentGateway;
    private final ObjectProvider<TaskTracker> taskTrackerProvider;

    // Automatically injects all available DiscountStrategy beans via Spring
    private final List<DiscountStrategy> discountStrategies;

    public String checkTaskTrackerScope() {
        return "New TaskTracker ID: " + taskTrackerProvider.getObject().getTaskId();
    }

    @Transactional
    public String processOrder(int productId, int quantity) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < quantity) {
            return "Stock not sufficient!";
        }

        // 1. Calculate the initial total price
        BigDecimal rawTotalPrice = product.getPrice().multiply(new BigDecimal(quantity));

        // 2. Find the appropriate strategy based on the product brand (Apple, Samsung, or default)
        DiscountStrategy targetStrategy = discountStrategies.stream()
                .filter(strategy -> strategy.supports(product.getBrand()))
                .findFirst()
                .orElse(new DefaultDiscountStrategy());

        // 3. Apply the discount
        BigDecimal finalPrice = targetStrategy.applyDiscount(rawTotalPrice);

        // 4. Send the final amount to the payment gateway
        double totalAmount = finalPrice.doubleValue();
        boolean paymentSuccess = paymentGateway.processPayment(totalAmount);

        if (paymentSuccess) {
            product.setStockQuantity(product.getStockQuantity() - quantity);

            if (product.getStockQuantity() == 0) {
                product.setProductAvailable(false);
            }

            productRepo.save(product);
            return "Order placed successfully! Total charged after discount: $" + totalAmount;
        } else {
            return "Payment failed!";
        }
    }
}