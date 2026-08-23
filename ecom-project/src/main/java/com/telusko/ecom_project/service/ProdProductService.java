package com.telusko.ecom_project.service;

import com.telusko.ecom_project.model.Product;
import com.telusko.ecom_project.repo.CommonProductRepo;
import com.telusko.ecom_project.repo.ProductRepo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("prod")
public class ProdProductService extends ProductService<Product> {

    public ProdProductService(CommonProductRepo<Product> repo) {
        super(repo);
    }

    @Override
    public Product addProduct(String productJson, MultipartFile imageFile) throws IOException {
        Product product = objectMapper.readValue(productJson, Product.class);

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
}