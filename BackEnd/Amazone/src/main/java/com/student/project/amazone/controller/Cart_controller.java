package com.student.project.amazone.controller;

import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.service.Cart_service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    Map<Object, Object> respone = new HashMap<>();
    cartModel cartData = new cartModel();

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
    public ResponseEntity<Map<Object, Object>> add(@RequestBody cartModel requestCart) {

        String message = "Thêm thành công vào giỏ hàng";

        HttpStatus status = HttpStatus.OK;
        try {
            cartData = service.save(requestCart);
            respone.put("uniqueItemInCart", cartData.getCartItem().size() + "");
            respone.put("cartData", cartData);
            respone.put("message", message);
        } catch (Exception ex) {
            respone.put("message", ex.getMessage());
            status = HttpStatus.REQUEST_TIMEOUT;
        }
        return ResponseEntity.status(status).body(respone);
    }

    @PatchMapping
    public ResponseEntity<Map<Object, Object>> patch(@RequestParam String userId, @RequestParam String itemId, @RequestBody Map<Object, Object> fields) {
        String message = "Cập nhật thành công";
        HttpStatus status = HttpStatus.OK;
        try {
            cartData = service.update(itemId, Long.valueOf(userId), fields);
            respone.put("uniqueItemInCart", cartData.getCartItem().size() + "");
            respone.put("cartData", cartData);
            respone.put("message", message);
        } catch (Exception ex) {
            respone.put("message", ex.getMessage());
            status = HttpStatus.REQUEST_TIMEOUT;
        }
        return ResponseEntity.status(status).body(respone);
    }

    @DeleteMapping
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String CartId, @RequestBody List<Long> Itemid) {
        String message = "Xóa thành công";
        HttpStatus status = HttpStatus.OK;
        try {
            cartData = service.cartByCartId(Long.parseLong(CartId));

            if (cartData.getCartItem().size() != 0) {
                service.ItemDelete(valueOf(CartId), Itemid);
            }
            respone.put("uniqueItemInCart", cartData.getCartItem().size() + "");
            respone.put("message", message);
        } catch (Exception ex) {
            message = ex.getMessage();
            respone.put("message", message);
            status = HttpStatus.REQUEST_TIMEOUT;
        }
        return ResponseEntity.status(status).body(respone);


    }
}
