package com.telusko.ecom_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telusko.ecom_project.repo.CommonProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public abstract class ProductService<T> {

    protected final CommonProductRepo<T> repo;
    protected final ObjectMapper objectMapper;

    public List<T> getAllProducts() {
        return repo.findAll();
    }

    public T getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public abstract T addProduct(String productJson, MultipartFile imageFile) throws IOException;

    public abstract T updateProduct(int id, String productJson, MultipartFile imageFile) throws IOException;

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public abstract List<T> searchProducts(String keyword);

    public abstract T updateProductPrice(int id, BigDecimal newPrice);
}