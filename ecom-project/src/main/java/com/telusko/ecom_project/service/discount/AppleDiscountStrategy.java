package com.telusko.ecom_project.service.discount;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@Order(1)
public class AppleDiscountStrategy implements DiscountStrategy {

    @Override
    public boolean supports(String brand) {
        return "Apple".equalsIgnoreCase(brand);
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        return price.multiply(new BigDecimal("0.90"));
    }
}