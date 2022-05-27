package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.Order_model;
import com.student.project.amazone.entity.orderItem_model;

import java.util.List;

public interface Order_service {
    public Order_model save(Order_model order_model);

    public List<Order_model> all();

    public Order_model one(Long userId);

    public Order_model oneByOrderId(Long orderId);
}
