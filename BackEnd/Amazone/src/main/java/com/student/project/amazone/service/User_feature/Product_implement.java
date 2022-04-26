package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.repo.Product_modelRepository;
import com.sun.jersey.api.ConflictException;
import com.sun.jersey.api.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Product_implement implements Product_service {
    private final Product_modelRepository service;

    public Product_implement(Product_modelRepository service) {
        this.service = service;
    }

    @Override
    public Product_model saveProduct(Product_model product) {
        if (service.findByName(product.getName()) == null)
            return service.save(product);
        throw new ConflictException("Product already exists");
    }

    @Override
    public List<Product_model> findAllProduct(Pageable pagingProducts) {
        return service.findAllProducts(pagingProducts);
    }

    @Override
    public Product_model findUserById(Long id) {
        if (service.findProductById(id) != null) return service.findProductById(id);
        throw new NotFoundException("Product not exists");
    }

    @Override
    public void deleteProduct(Product_model prdocut) {
        if (service.findProductById(prdocut.getId()) != null) service.delete(prdocut);
        throw new NotFoundException("Product not exists");
    }

    @Override
    public Product_model findUserByName(String name) {
        return service.findByName(name);
    }
}
