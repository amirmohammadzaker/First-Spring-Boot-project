package com.telusko.ecom_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telusko.ecom_project.exception.ProductNotFoundException;
import com.telusko.ecom_project.model.Review;
import com.telusko.ecom_project.repo.CommonProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public Page<T> getProductsPaged(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public T getProductById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("محصولی با شناسه مورد نظر یافت نشد"));
    }
    public abstract List<T> getProductsSortedByReleaseDate();

    public abstract T addProduct(String productJson, MultipartFile imageFile) throws IOException;

    public abstract T updateProduct(int id, String productJson, MultipartFile imageFile) throws IOException;

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public abstract List<T> searchProducts(String keyword);

    public abstract T updateProductPrice(int id, BigDecimal newPrice);
    public abstract ResponseEntity<byte[]> downloadImage(int id);
    public abstract Review addReviewToProduct(int productId, Review review);
    public abstract T addTagToProduct(int productId, Long tagId);
    public abstract T removeTagFromProduct(int productId, Long tagId);
    public abstract List<T> getProductsByTagId(Long tagId);
    public abstract List<T> getAllProductsWithReviewsAndTags();
}