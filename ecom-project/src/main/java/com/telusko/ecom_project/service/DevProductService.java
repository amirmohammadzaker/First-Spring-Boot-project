package com.telusko.ecom_project.service;

import com.telusko.ecom_project.model.DevProduct;
import com.telusko.ecom_project.repo.CommonProductRepo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Profile("dev")
public class DevProductService extends ProductService<DevProduct> {

    public DevProductService(CommonProductRepo<DevProduct> repo) {
        super(repo);
    }

    @Override
    public DevProduct updateProduct(int id, DevProduct product, MultipartFile imageFile) throws IOException {
        DevProduct existingProduct = getProductById(id);

        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDevNotes(product.getDevNotes());

        return repo.save(existingProduct);
    }
}