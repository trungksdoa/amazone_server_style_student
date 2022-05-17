package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.entity.Users_model;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Product_modelRepository extends JpaRepository<Product_model, Long> {
    Product_model findByName(String name);

    void deleteProductById(Long id);
    @Query(value = "SELECT * FROM product_model WHERE id = ?1",
            nativeQuery = true)
    public Product_model findProductById(Long id);

    @Query(value = "SELECT * FROM product_model", nativeQuery = true)
    public List<Product_model> findAllProducts();
}