package com.student.project.amazone.service;

import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.repo.CartItemDtoRepository;
import com.student.project.amazone.repo.Cart_modelRepository;
import com.sun.jersey.api.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class Cart_implement implements Cart_service {


    private final Cart_modelRepository cart_modelRepository;
    private final CartItemDtoRepository cartItemDtoRepository;

    public Cart_implement(Cart_modelRepository cart_modelRepository, CartItemDtoRepository cartItemDtoRepository) {
        this.cart_modelRepository = cart_modelRepository;
        this.cartItemDtoRepository = cartItemDtoRepository;
    }

    public boolean inCartItem(cartModel cartModel) {
        boolean itemExists = false;
        if (!cartModel.getCartItem().isEmpty()) {
            itemExists = true;
        }
        return itemExists;
    }

    @Override
    public cartModel cartByUserId(Long userId) {
        cartModel cartExist = cart_modelRepository.findCart_modelByUserId(userId);
        return cartExist;
    }

    @Override
    public cartItem cartByProductId(Long productId) {
        cartItem cartExist = cartItemDtoRepository.findByProductId(productId);
        return cartExist;
    }

    @Override
    public cartModel saveOrUpdate(cartItem cartitem, Long userId) {
        cartModel cartExist = cartByUserId(userId);
        if (cartExist != null) {
            if (cartExist.getCartItem().size() == 0) {
                cartExist.getCartItem().add(cartitem);
            } else {
                cartExist.getCartItem().stream()
                        .filter(item -> item.getProductItem().getId().equals(cartitem.getProductItem().getId()))
                        .forEach(item -> {
                            item.setQuantityItemNumber(cartitem.getQuantityItemNumber());
                        });
            }
            return cart_modelRepository.save(cartExist);
        } else {
            cartModel newCart = new cartModel(userId);
            newCart.getCartItem().add(cartitem);
            return cart_modelRepository.save(newCart);
        }
    }

    @Override
    public void ItemDelete(long CartId, List<Long> itemId) {
        cartModel cartitem = cart_modelRepository.getById(CartId);
        if (inCartItem(cartitem)) {

            List<cartItem> deletedItems = cartitem.getCartItem().stream()
                    .filter(e -> itemId.contains(e.getId()))
                    .collect(Collectors.toList());
            cartitem.getCartItem().removeAll(deletedItems);
        }
    }

    @Override
    public List<cartModel> cartList() {
        List<cartModel> cart = cart_modelRepository.findAll();
        return cart;
    }
}
