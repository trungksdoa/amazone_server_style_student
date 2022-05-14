package com.student.project.amazone.controller;

import com.student.project.amazone.entity.CartItem;
import com.student.project.amazone.entity.Cart_model;
import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.service.User_feature.Cart_service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.lang.Long.valueOf;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class Cart_controller {

    @Autowired
    private Cart_service service;

    @GetMapping
    public ResponseEntity<Map<Users_model, Cart_model>> userMap() {
        return ResponseEntity.ok().body(service.cartMap());
    }

    @PostMapping
    public ResponseEntity<Cart_model> AddToCart(@RequestBody CartItem cartItem, @RequestParam String userId) {
        return ResponseEntity.ok().body(service.CartSave(cartItem,Long.valueOf(userId)));
    }

    @PutMapping
    public ResponseEntity<Cart_model> UpdateCart(@RequestParam String CartId, @RequestParam String Itemid, @RequestParam String amount) {
        Cart_model cartModel = service.CartUpdate(valueOf(CartId), valueOf(Itemid), Integer.valueOf(amount));
        return ResponseEntity.ok().body(cartModel);
    }

    @DeleteMapping
    public ResponseEntity<String> DeleteItemInCart(@RequestParam String CartId, @RequestParam String Itemid){
        service.ItemDelete(valueOf(CartId),valueOf(Itemid));
        return ResponseEntity.ok().body("Deletes success");
    }
}
