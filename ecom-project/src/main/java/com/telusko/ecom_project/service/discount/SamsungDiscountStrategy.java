package com.telusko.ecom_project.service.discount;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@Order(2)
public class SamsungDiscountStrategy implements DiscountStrategy {

    @Override
    public boolean supports(String brand) {
        return "Samsung".equalsIgnoreCase(brand);
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        return price.subtract(new BigDecimal("50")).max(BigDecimal.ZERO);
    }
}