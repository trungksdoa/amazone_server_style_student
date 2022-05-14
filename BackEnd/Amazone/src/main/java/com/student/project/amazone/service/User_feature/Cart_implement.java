package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.CartItem;
import com.student.project.amazone.entity.Cart_model;
import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.repo.CartItemDtoRepository;
import com.student.project.amazone.repo.Cart_modelRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class Cart_implement implements Cart_service {


    private final Cart_modelRepository cart_modelRepository;
    private final CartItemDtoRepository cartItemDtoRepository;

    public Cart_implement(Cart_modelRepository cart_modelRepository, CartItemDtoRepository cartItemDtoRepository) {
        this.cart_modelRepository = cart_modelRepository;
        this.cartItemDtoRepository = cartItemDtoRepository;
    }

    public boolean inCartItem(Cart_model cartModel) {
        boolean itemExists = false;
        if (!cartModel.getCartItem().isEmpty()) {
            itemExists = true;
        }
        return itemExists;
    }

    @Override
    public Cart_model CartSave(CartItem cartitem, Long userId) {
        Cart_model cartExist = cart_modelRepository.findCart_modelByUserId(userId);
        if (cartExist != null) {
            cartExist.getCartItem().stream().forEach(
                    data -> {
                        if (data.getProductItem().getId().equals(cartitem.getProductItem().getId())) {
                            data.setQuantityItemNumber(cartitem.getQuantityItemNumber());
                        } else {
                            cartExist.getCartItem().add(cartitem);
                        }
                    });
            return cart_modelRepository.save(cartExist);
        } else {
            Cart_model newCart = new Cart_model(true, userId);
            newCart.getCartItem().add(cartitem);
            return cart_modelRepository.save(newCart);
        }
    }

    @Override
    public Cart_model CartUpdate(Long CartId, Long itemId, int quantity) {
        Cart_model cartitem = cart_modelRepository.getById(CartId);
        if (inCartItem(cartitem)) {
            cartitem.getCartItem().stream().forEach(
                    data -> {
                        if (data.getId().equals(itemId)) {
                            data.setQuantityItemNumber(quantity);
                        }
                    }
            );
        }
        return cart_modelRepository.save(cartitem);
    }

    @Override
    public void ItemDelete(Long CartId, Long itemId) {
        Cart_model cartitem = cart_modelRepository.getById(CartId);
        if (inCartItem(cartitem)) {
            cartitem.getCartItem().removeIf(item -> item.getId().equals(itemId));
            cartitem.getCartItem().stream().filter(item -> item.getId().equals("223")).forEach(System.out::println);
            System.out.println(cartitem.getCartItem().size());
            cart_modelRepository.save(cartitem);
        }
    }

    @Override
    public Map<Users_model, Cart_model> cartMap() {
        Map<Users_model, Cart_model> mapItem = new HashMap<>();
        List<Cart_model> cart = cart_modelRepository.findAll();
        if (cart != null) {
            for (Cart_model items : cart) {
                mapItem.put(items.getUserId(), items);
            }
        }
        return mapItem;
    }
}
