package com.telusko.ecom_project.service.discount;

import java.math.BigDecimal;

public interface DiscountStrategy {
    boolean supports(String brand);
    BigDecimal applyDiscount(BigDecimal price);
}