package com.telusko.ecom_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telusko.ecom_project.exception.ProductNotFoundException;
import com.telusko.ecom_project.model.Product;
import com.telusko.ecom_project.model.Review;
import com.telusko.ecom_project.repo.CommonProductRepo;
import com.telusko.ecom_project.repo.ProductRepo;
import com.telusko.ecom_project.repo.ReviewRepo;
import com.telusko.ecom_project.validation.ProdChecks;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@Profile("prod")
public class ProdProductService extends ProductService<Product> {

    private final Validator validator;
    private final ReviewRepo reviewRepo;

    public ProdProductService(CommonProductRepo<Product> repo,
                              ObjectMapper objectMapper,
                              Validator validator,
                              ReviewRepo reviewRepo) {
        super(repo, objectMapper);
        this.validator = validator;
        this.reviewRepo = reviewRepo;
    }

    private void validate(Product product) {
        Set<ConstraintViolation<Product>> violations = validator.validate(product, ProdChecks.class);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }


    @Override
    public Review addReviewToProduct(int productId, Review review) {
        Product product = getProductById(productId);
        if (product == null) {
            throw new ProductNotFoundException("محصولی با شناسه " + productId + " یافت نشد.");
        }

        review.setProduct(product);

        return reviewRepo.save(review);
    }

    @Override
    public Product addProduct(String productJson, MultipartFile imageFile) throws IOException {
        Product product = objectMapper.readValue(productJson, Product.class);

        validate(product);

        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
        }
        return repo.save(product);
    }

    @Override
    public Product updateProduct(int id, String productJson, MultipartFile imageFile) throws IOException {
        Product productDto = objectMapper.readValue(productJson, Product.class);

        validate(productDto);

        Product existingProduct = getProductById(id);
        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setReleaseDate(productDto.getReleaseDate());
        existingProduct.setProductAvailable(productDto.isProductAvailable());
        existingProduct.setStockQuantity(productDto.getStockQuantity());

        if (imageFile != null && !imageFile.isEmpty()) {
            existingProduct.setImageName(imageFile.getOriginalFilename());
            existingProduct.setImageType(imageFile.getContentType());
            existingProduct.setImageData(imageFile.getBytes());
        }

        return repo.save(existingProduct);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return ((ProductRepo) repo).searchProducts(keyword);
    }

    @Override
    public Product updateProductPrice(int id, BigDecimal newPrice) {
        Product existingProduct = getProductById(id);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setPrice(newPrice);
        return repo.save(existingProduct);
    }

    @Override
    public ResponseEntity<byte[]> downloadImage(int id) {
        Product product = getProductById(id);

        if (product.getImageData() == null || product.getImageData().length == 0) {
            throw new ProductNotFoundException("تصویری برای این محصول یافت نشد");
        }

        String fileName = product.getImageName() != null ? product.getImageName() : "file";
        String fileType = product.getImageType() != null ? product.getImageType() : "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(product.getImageData());
    }
}