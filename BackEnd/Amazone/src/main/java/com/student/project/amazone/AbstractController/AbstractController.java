package com.student.project.amazone.AbstractController;

import com.student.project.amazone.entity.Order_model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public abstract class AbstractController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Order_model>> getOrder() {
        return null;
    }

    @RequestMapping(value = "", params = "userId", method = RequestMethod.GET)
    public ResponseEntity<Order_model> getOrder(@RequestParam(name = "userId", required = false) Optional<Long> userId) {
        System.out.println(userId.get());
        return null;
    }
}
