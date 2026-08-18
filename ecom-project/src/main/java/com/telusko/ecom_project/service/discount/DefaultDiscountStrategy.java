package com.telusko.ecom_project.service.discount;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@Order(3)
public class DefaultDiscountStrategy implements DiscountStrategy {

    @Override
    public boolean supports(String brand) {
        return true;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        return price;
    }
}