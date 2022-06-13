package com.student.project.amazone.controller;

import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.service.Cart_service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.valueOf;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
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


    @GetMapping("/mini")
    public ResponseEntity<Map<String, String>> mapMini(@RequestParam String userId) {

        Map<String, String> fields = new HashMap<String, String>();

        cartModel cartModel = service.cartByUserId(Long.valueOf(userId));

        fields.put("uniqueItemInCart", cartModel.getCartItem().size() + "");
        return ResponseEntity.ok().body(fields);
    }


    @PutMapping
    public ResponseEntity<cartModel> add(@RequestBody cartModel requestCart) {
        return ResponseEntity.ok().body(service.save(requestCart));
    }

    @PatchMapping
    public ResponseEntity<cartModel> patch(@RequestParam String userId, @RequestParam String itemId, @RequestBody Map<Object, Object> fields) {
        return ResponseEntity.ok().body(service.update(itemId, Long.valueOf(userId), fields));
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> delete(@RequestParam String CartId, @RequestBody List<Long> Itemid) {


        cartModel cartModel = service.cartByCartId(Long.parseLong(CartId));

        if(cartModel.getCartItem().size() != 0){
            service.ItemDelete(valueOf(CartId), Itemid);
        }
        Map<String, String> fields = new HashMap<String, String>();

        fields.put("uniqueItemInCart", cartModel.getCartItem().size() + "");
        fields.put("message", "Delete item in cart success");
        return ResponseEntity.ok().body(fields);
    }
}
