package com.telusko.ecom_project.repo;

import com.telusko.ecom_project.model.DevProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevProductRepo extends CommonProductRepo<DevProduct> {
}