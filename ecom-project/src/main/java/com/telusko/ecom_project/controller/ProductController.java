package com.telusko.ecom_project.controller;

import com.telusko.ecom_project.model.Review;
import com.telusko.ecom_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController<T> {

    private final ProductService<T> productService;

    @GetMapping("/products")
    public ResponseEntity<List<T>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/paged")
    public ResponseEntity<List<T>> getProductsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return new ResponseEntity<>(productService.getProductsPaged(PageRequest.of(page, size, sort)).getContent(), HttpStatus.OK);
    }

    @GetMapping("/products/by-release-date")
    public ResponseEntity<List<T>> getProductsSortedByReleaseDate() {
        List<T> products = productService.getProductsSortedByReleaseDate();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<T> getProductById(@PathVariable int id) {
        T product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(
            @RequestPart("product") String productJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            T savedProduct = productService.addProduct(productJson, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(
            @PathVariable int id,
            @RequestPart("product") String productJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            T updatedProduct = productService.updateProduct(id, productJson, imageFile);
            if (updatedProduct != null) {
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        T product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<T>> searchProducts(@RequestParam String keyword) {
        List<T> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PatchMapping("/product/{id}/price")
    public ResponseEntity<?> updateProductPrice(
            @PathVariable int id,
            @RequestParam BigDecimal price) {

        T updatedProduct = productService.updateProductPrice(id, price);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{id}/image/download")
    public ResponseEntity<byte[]> downloadProductImage(@PathVariable int id) {
        return productService.downloadImage(id);
    }

    @PostMapping("/product/{id}/reviews")
    public ResponseEntity<Review> addReviewToProduct(
            @PathVariable int id,
            @RequestBody Review review) {
        Review savedReview = productService.addReviewToProduct(id, review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @PostMapping("/product/{productId}/tags/{tagId}")
    public ResponseEntity<T> addTagToProduct(
            @PathVariable int productId,
            @PathVariable Long tagId) {
        T updatedProduct = productService.addTagToProduct(productId, tagId);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}/tags/{tagId}")
    public ResponseEntity<T> removeTagFromProduct(
            @PathVariable int productId,
            @PathVariable Long tagId) {
        T updatedProduct = productService.removeTagFromProduct(productId, tagId);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/products/by-tag/{tagId}")
    public ResponseEntity<List<T>> getProductsByTagId(@PathVariable Long tagId) {
        List<T> products = productService.getProductsByTagId(tagId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}