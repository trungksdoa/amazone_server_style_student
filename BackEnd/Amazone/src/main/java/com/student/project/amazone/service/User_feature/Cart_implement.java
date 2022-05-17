package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.repo.CartItemDtoRepository;
import com.student.project.amazone.repo.Cart_modelRepository;
import com.sun.jersey.api.NotFoundException;
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
    public cartModel saveOrUpdate(cartItem cartitem, Long userId) {

        cartModel cartExist = cartByUserId(userId);
        if (cartExist != null) {
            cartItem item = cartItemDtoRepository.findByProductId(cartitem.getProductItem().getId());
            cartItemDtoRepository.save(item);
            cartExist = cartByUserId(userId);
        } else {
            cartExist = new cartModel(userId);
            cartExist.getCartItem().add(cartitem);
            cartExist = cart_modelRepository.save(cartExist);
        }
        return cartExist;
//        cartModel cartExist = cartByUserId(userId);
//        if (cartExist != null) {
//            cartExist.getCartItem().forEach(
//                    data -> {
//                        if (data.getProductItem().getId().equals(cartitem.getProductItem().getId())) {
//                            data.setQuantityItemNumber(cartitem.getQuantityItemNumber());
//                        } else {
//                            cartExist.getCartItem().add(cartitem);
//                        }
//                    });
//            return cart_modelRepository.save(cartExist);
//        } else {
//            cartModel newCart = new cartModel(userId);
//            newCart.getCartItem().add(cartitem);
//            return cart_modelRepository.save(newCart);
//        }
    }

    @Override
    public void ItemDelete(Long CartId, Long itemId) {
        cartModel cartitem = cart_modelRepository.getById(CartId);
        if (inCartItem(cartitem)) {
            cartitem.getCartItem().removeIf(item -> item.getId().equals(itemId));
            cartitem.getCartItem().stream().filter(item -> item.getId().equals("223")).forEach(System.out::println);
            System.out.println(cartitem.getCartItem().size());
            cart_modelRepository.save(cartitem);
        }
    }

    @Override
    public List<cartModel> cartList() {
        List<cartModel> cart = cart_modelRepository.findAll();
        return cart;
    }
}
