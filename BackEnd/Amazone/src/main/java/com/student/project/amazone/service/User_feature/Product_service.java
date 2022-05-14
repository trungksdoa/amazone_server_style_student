package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.Product_model;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Product_service {
    public Product_model saveProduct(Product_model user);

    public List<Product_model> findAllProduct();

    public Product_model findUserById(Long id);

    public void deleteProduct(Product_model user);

    public Product_model findUserByName(String name);
}
