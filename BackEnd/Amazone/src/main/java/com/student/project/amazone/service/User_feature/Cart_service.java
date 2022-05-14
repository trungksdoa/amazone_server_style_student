package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.CartItem;
import com.student.project.amazone.entity.Cart_model;
import com.student.project.amazone.entity.Users_model;

import java.util.Map;

public interface Cart_service {
    Cart_model CartSave(CartItem cartitem, Long userId);
    Cart_model CartUpdate(Long CartId ,Long Itemid,int quantity);
    void ItemDelete(Long CartId, Long itemId);
    Map<Users_model,Cart_model> cartMap();

}
