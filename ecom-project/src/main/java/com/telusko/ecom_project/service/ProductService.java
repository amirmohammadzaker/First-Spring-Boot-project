package com.telusko.ecom_project.service;

import com.telusko.ecom_project.repo.CommonProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public abstract class ProductService<T> {

    protected final CommonProductRepo<T> repo;

    public List<T> getAllProducts() {
        return repo.findAll();
    }

    public T getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public T addProduct(T product, MultipartFile imageFile) throws IOException {
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<T> searchProducts(String keyword) {
        return List.of();
    }
    public abstract T updateProduct(int id, T product, MultipartFile imageFile) throws IOException;
}