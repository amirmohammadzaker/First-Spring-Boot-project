package com.telusko.ecom_project.service.discount;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
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