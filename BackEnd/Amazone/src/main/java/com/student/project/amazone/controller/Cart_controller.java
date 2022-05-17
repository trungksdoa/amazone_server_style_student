package com.student.project.amazone.controller;

import com.student.project.amazone.entity.cartItem;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.service.User_feature.Cart_service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static java.lang.Long.valueOf;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class Cart_controller {

    @Autowired
    private Cart_service service;

    @GetMapping
    public ResponseEntity<cartModel> userMap(@RequestParam String userId) {
        return ResponseEntity.ok().body(service.cartByUserId(Long.valueOf(userId)));
    }

    @GetMapping("/All")
    public ResponseEntity<List<cartModel>> map() {
        return ResponseEntity.ok().body(service.cartList());
    }

    @PostMapping
    public ResponseEntity<cartModel> add(@RequestBody cartItem cartItem, @RequestParam String userId) {
        return ResponseEntity.ok().body(service.saveOrUpdate(cartItem, Long.valueOf(userId)));
    }


    @PatchMapping
    public ResponseEntity<cartModel> patch(@RequestParam String userId, @RequestBody  Map<Object, Object> fields) {
        cartModel cartModel = service.cartByUserId(Long.valueOf(userId));
        cartModel.getCartItem().forEach(item -> {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(cartItem.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, item, value);
            });
            service.saveOrUpdate(item, Long.valueOf(userId));
        });
        return ResponseEntity.ok().body(cartModel);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String CartId, @RequestParam String Itemid) {
        service.ItemDelete(valueOf(CartId), valueOf(Itemid));
        return ResponseEntity.ok().body("Deletes success");
    }
}
