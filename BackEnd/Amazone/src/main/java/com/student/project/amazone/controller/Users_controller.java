package com.student.project.amazone.controller;


import com.student.project.amazone.entity.Users_model;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:1212")
public class Users_controller {

    @GetMapping
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok().body("Login success");
    }

    @PostMapping("login")
    public ResponseEntity<Users_model> loginUsers(@RequestBody Users_model user) {
        if (user.getName().equals("admin") && user.getPassword().equals("admin")) {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/login").toUriString());
            return ResponseEntity.created(uri).body(user);
        }else{
            throw new IllegalStateException("Login fails");
        }
    }
}
