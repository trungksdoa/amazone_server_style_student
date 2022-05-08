package com.student.project.amazone.controller;

import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Product_model;
import com.student.project.amazone.service.User_feature.Catagory_service;
import com.student.project.amazone.service.User_feature.Product_service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catagory/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class Catagory_controller {
    private final Catagory_service service;

    @GetMapping("all")
    public ResponseEntity<List<Catagory_model>> getAllCatagory() {

        List<Catagory_model> cata = service.findAllCategory();
        return new ResponseEntity<>(cata, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Catagory_model> saveProduct(@RequestBody Catagory_model catagory_model) {
        return ResponseEntity.ok().body(service.saveCatagory(catagory_model));
    }
}
