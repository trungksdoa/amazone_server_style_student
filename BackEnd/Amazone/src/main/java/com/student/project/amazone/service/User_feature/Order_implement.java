package com.student.project.amazone.service.User_feature;


import com.student.project.amazone.entity.Order_model;
import com.student.project.amazone.repo.Order_modelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Order_implement implements Order_service {

    public final Order_modelRepository modelRepository;

    public Order_implement(Order_modelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    @Override
    public Order_model save(Order_model order_model) {
        return modelRepository.save(order_model);
    }

    @Override
    public List<Order_model> all() {
        return modelRepository.findAll();
    }

    @Override
    public Order_model one(Long userId) {
        return modelRepository.findByUserId(userId);
    }

    @Override
    public Order_model oneByOrderId(Long orderId) {
        if (modelRepository.findById(orderId).isPresent()) {
            return modelRepository.findById(orderId).get();
        }
        throw new IllegalStateException("Not found ID");
    }
}
