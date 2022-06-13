package com.student.project.amazone.service;

import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;

import java.util.List;
import java.util.Map;

public interface Cart_service {
    cartModel cartByCartId(Long cartId);
    cartModel cartByUserId(Long userId);
    cartItem cartByProductId(Long productId);

    cartModel save(cartModel newCart);

    cartModel update(String itemId, Long userId, Map<Object, Object> fields);

    cartItem updateCartItem(cartItem cartitem);
    void ItemDelete(long CartId, List<Long> itemId);
    List<cartModel> cartList();

}
