package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.entity.Users_model;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Product_modelRepository extends JpaRepository<Product_model, Long> {
    Product_model findByName(String name);

    void deleteProductById(Long id);

    @Query("FROM Product_model c where c.catagory.id = :catagory")
    List<Product_model> findProductByCartId(@Param("catagory") Long id);

    @Query(value="select * from product u where u.name LIKE %:name%",nativeQuery=true)
    List<Product_model> findProductsByName(@Param("name") String name);
    @Query(value = "SELECT * FROM product_model WHERE id = ?1",
            nativeQuery = true)
    public Product_model findProductById(Long id);

    @Query(value = "SELECT * FROM product_model", nativeQuery = true)
    public List<Product_model> findAllProducts();
}