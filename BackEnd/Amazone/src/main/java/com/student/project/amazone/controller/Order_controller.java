package com.student.project.amazone.controller;


import com.student.project.amazone.AbstractController.AbstractController;
import com.student.project.amazone.entity.Order_model;
import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.orderItem_model;
import com.student.project.amazone.service.User_feature.Cart_service;
import com.student.project.amazone.service.User_feature.Order_service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class Order_controller  extends AbstractController {
    @Autowired
    private Order_service service;

    @PostMapping
    public ResponseEntity<Order_model> saveOrder(@RequestBody Order_model requestOrder) {
        return ResponseEntity.ok().body(service.save(requestOrder));
    }


    @Override
    public ResponseEntity<List<Order_model>> getOrder() {
        return ResponseEntity.ok().body(service.all());
    }

    @Override
    public ResponseEntity<Order_model> getOrder(Optional<Long> userId) {
        return ResponseEntity.ok().body(service.one(userId.get()));
    }


}
