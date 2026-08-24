package com.telusko.ecom_project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telusko.ecom_project.validation.ProdChecks;
import com.telusko.ecom_project.validation.ValidReleaseDate;
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

    @NotBlank(message = "Product name cannot be blank", groups = ProdChecks.class)
    private String name;

    private String description;

    @NotBlank(message = "Brand cannot be blank", groups = ProdChecks.class)
    private String brand;

    @NotNull(message = "Price is required", groups = ProdChecks.class)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero", groups = ProdChecks.class)
    @DecimalMax(value = "100000.0", message = "Price cannot exceed 100,000", groups = ProdChecks.class)
    private BigDecimal price;

    @NotBlank(message = "Category cannot be blank", groups = ProdChecks.class)
    private String category;

    @NotNull(message = "تاریخ انتشار الزامی است")
    @ValidReleaseDate
    @JsonProperty("release_Date")
    private Date releaseDate;

    private boolean productAvailable;

    @Min(value = 0, message = "Stock quantity cannot be negative", groups = ProdChecks.class)
    private int stockQuantity;

    private String imageName;

    private String imageType;

    @Lob
    private byte[] imageData;
}