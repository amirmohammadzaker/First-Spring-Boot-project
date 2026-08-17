package com.telusko.ecom_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonProductRepo<T> extends JpaRepository<T, Integer> {
}