package com.student.project.amazone.service;

import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;

import java.util.List;

public interface Cart_service {
    cartModel cartByUserId(Long userId);
    cartItem cartByProductId(Long productId);
    cartModel saveOrUpdate(cartItem cartitem, Long userId);
    void ItemDelete(long CartId, List<Long> itemId);
    List<cartModel> cartList();

}
