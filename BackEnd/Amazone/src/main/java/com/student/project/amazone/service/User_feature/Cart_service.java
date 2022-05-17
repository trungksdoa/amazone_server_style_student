package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.entity.Users_model;

import java.util.List;
import java.util.Map;

public interface Cart_service {
    cartModel cartByUserId(Long userId);
    cartModel saveOrUpdate(cartItem cartitem, Long userId);
    void ItemDelete(Long CartId, Long itemId);
    List<cartModel> cartList();

}
