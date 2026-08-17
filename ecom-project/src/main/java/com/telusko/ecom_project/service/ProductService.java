package com.telusko.ecom_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telusko.ecom_project.repo.CommonProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public abstract class ProductService<T> {

    protected final CommonProductRepo<T> repo;

    @Autowired
    protected ObjectMapper objectMapper;

    public ProductService(CommonProductRepo<T> repo) {
        this.repo = repo;
    }

    public List<T> getAllProducts() {
        return repo.findAll();
    }

    public T getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public abstract T addProduct(String productJson, MultipartFile imageFile) throws IOException;

    public abstract T updateProduct(int id, String productJson, MultipartFile imageFile) throws IOException;

    public abstract List<T> searchProducts(String keyword);
}