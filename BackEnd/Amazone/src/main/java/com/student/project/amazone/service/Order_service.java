package com.student.project.amazone.service;

import com.student.project.amazone.dto.Order_modelInfo;
import com.student.project.amazone.entity.ChartOption;
import com.student.project.amazone.entity.Order_model;
import com.student.project.amazone.entity.orderItem_model;

import java.util.List;

public interface Order_service {
    public Order_model save(Order_model order_model);

    public List<Order_model> all();

    public List<Order_model> all(long userId);

    public List<Order_model> getTop4OrderByUserId(long userId);

//    public Order_model one(Long userId);

    public Order_model oneByOrderId(Long orderId);

    public ChartOption  getValueIn12Month(long userId);
}
