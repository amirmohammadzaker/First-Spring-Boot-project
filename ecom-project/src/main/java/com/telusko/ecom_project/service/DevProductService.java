package com.telusko.ecom_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telusko.ecom_project.model.DevProduct;
import com.telusko.ecom_project.model.Review;
import com.telusko.ecom_project.repo.CommonProductRepo;
import com.telusko.ecom_project.validation.DevChecks;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@Profile("dev")
public class DevProductService extends ProductService<DevProduct> {

    private final Validator validator;

    public DevProductService(CommonProductRepo<DevProduct> repo, ObjectMapper objectMapper, Validator validator) {
        super(repo, objectMapper);
        this.validator = validator;
    }

    private void validate(DevProduct product) {
        Set<ConstraintViolation<DevProduct>> violations = validator.validate(product, DevChecks.class);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @Override
    public Review addReviewToProduct(int productId, Review review) {
        throw new UnsupportedOperationException("ثبت نظر برای محصول در محیط Dev پشتیبانی نمی‌شود");
    }

    @Override
    public DevProduct addProduct(String productJson, MultipartFile imageFile) throws IOException {
        DevProduct product = objectMapper.readValue(productJson, DevProduct.class);
        validate(product);
        return repo.save(product);
    }

    @Override
    public DevProduct updateProduct(int id, String productJson, MultipartFile imageFile) throws IOException {
        DevProduct productDto = objectMapper.readValue(productJson, DevProduct.class);
        validate(productDto);

        DevProduct existingProduct = getProductById(id);
        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setDevNotes(productDto.getDevNotes());

        return repo.save(existingProduct);
    }

    @Override
    public List<DevProduct> searchProducts(String keyword) {
        return repo.findAll();
    }

    @Override
    public DevProduct updateProductPrice(int id, BigDecimal newPrice) {
        DevProduct existingProduct = getProductById(id);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setPrice(newPrice);
        return repo.save(existingProduct);
    }

    @Override
    public ResponseEntity<byte[]> downloadImage(int id) {
        throw new UnsupportedOperationException("دانلود تصویر در محیط Dev پشتیبانی نمی‌شود");
    }

    @Override
    public DevProduct addTagToProduct(int productId, Long tagId) {
        throw new UnsupportedOperationException("مدیریت تگ‌ها در محیط Dev پشتیبانی نمی‌شود");
    }

    @Override
    public DevProduct removeTagFromProduct(int productId, Long tagId) {
        throw new UnsupportedOperationException("مدیریت تگ‌ها در محیط Dev پشتیبانی نمی‌شود");
    }
    @Override
    public List<DevProduct> getProductsByTagId(Long tagId) {
        throw new UnsupportedOperationException("مدیریت تگ‌ها در محیط Dev پشتیبانی نمی‌شود");
    }
}