package com.telusko.ecom_project.service;

import com.telusko.ecom_project.model.DevProduct;
import com.telusko.ecom_project.repo.CommonProductRepo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@Profile("dev")
public class DevProductService extends ProductService<DevProduct> {

    public DevProductService(CommonProductRepo<DevProduct> repo) {
        super(repo);
    }

    @Override
    public DevProduct addProduct(String productJson, MultipartFile imageFile) throws IOException {
        DevProduct product = objectMapper.readValue(productJson, DevProduct.class);
        return repo.save(product);
    }

    @Override
    public DevProduct updateProduct(int id, String productJson, MultipartFile imageFile) throws IOException {
        DevProduct productDto = objectMapper.readValue(productJson, DevProduct.class);
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
    public java.util.List<DevProduct> searchProducts(String keyword) {
        return repo.findAll(); // یا متد اختصاصی سرچ Dev
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
}