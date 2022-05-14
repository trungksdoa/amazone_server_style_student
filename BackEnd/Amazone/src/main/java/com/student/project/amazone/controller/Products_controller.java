package com.student.project.amazone.controller;

import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.service.User_feature.Product_service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class Products_controller {
    private final Product_service service;

    @GetMapping("all")
    public ResponseEntity<List<Product_model>> getsProduct() {
        List<Product_model> product = service.findAllProduct();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

   @PostMapping("save")
    public ResponseEntity<Product_model> saveProduct(@RequestBody Product_model product_model) {
        return ResponseEntity.ok().body(service.saveProduct(product_model));
    }
}
