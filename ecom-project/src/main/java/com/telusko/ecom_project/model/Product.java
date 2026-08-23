package com.telusko.ecom_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank(message = "Product name cannot be blank")
    private String name;

    private String description;

    @NotNull
    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @DecimalMax(value = "100000.0", message = "Price cannot exceed 100,000")
    private BigDecimal price;

    @NotNull
    @NotBlank(message = "Category cannot be blank")
    private String category;

    private Date releaseDate;

    private boolean productAvailable;

    private int stockQuantity;

    private String imageName;

    private String imageType;
    @Lob
    private byte[] imageData;
}