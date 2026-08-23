package com.telusko.ecom_project.model;

import com.telusko.ecom_project.validation.DevChecks;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "dev_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required for Dev environment", groups = DevChecks.class)
    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50 characters", groups = DevChecks.class)
    private String name;

    @NotNull(message = "Price is required for Dev environment", groups = DevChecks.class)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero", groups = DevChecks.class)
    private BigDecimal price;

    @NotBlank(message = "Dev notes are required", groups = DevChecks.class)
    private String devNotes;
}