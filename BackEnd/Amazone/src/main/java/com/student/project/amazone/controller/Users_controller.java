package com.student.project.amazone.controller;


import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.service.Users_service;
import com.sun.jersey.api.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class Users_controller {
    private final Users_service service;


    Map<Object, Object> respone = new HashMap<>();
    cartModel cartData = new cartModel();

    @GetMapping
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok().body("Login success");
    }


    @GetMapping("isAdmin")
    public ResponseEntity<Boolean> checkIsAdmin(@RequestParam String username) {
        Users_model usersModel = service.findUserByName(username);
        if (usersModel != null) {
            if (usersModel.isAdmin()) {
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.ok().body(false);
    }

    @PatchMapping("banUser")
    public ResponseEntity<Boolean> bannedUser(@RequestParam String username) {
        Users_model usersModel = service.findUserByName(username);
        if (usersModel != null) {
            usersModel.setBanned(true);
            if (service.saveUser(usersModel).isBanned()) {
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.ok().body(false);
    }

    @GetMapping("isDeleted")
    public ResponseEntity<Boolean> checkIsDeleted(@RequestParam String username) {
        Users_model usersModel = service.findUserByName(username);
        if (usersModel != null) {
            if (usersModel.isDeleted()) {
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.ok().body(false);
    }

    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> loginUsers(@RequestBody Users_model user) {
        if (service.isLoggedIn(user)) {

            Users_model.userDto userDto = new Users_model.userDto(service.findUserByName(user.getUsername()));
            respone.put("user", userDto);
            respone.put("message", "Đăng nhập thành công, xin chào " + userDto.getUsername());
            return ResponseEntity.ok().body(respone);
        } else {
            throw new NotFoundException("Login fails");
        }
    }

    @PostMapping("save")
    public ResponseEntity<Users_model> saveUser(@RequestBody Users_model user) {
        service.saveUser(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Users_model> UpdateUser(@PathVariable String id, @RequestBody Users_model user) {
        user.setId(Long.valueOf(id));
        Users_model ues = service.findUserById(Long.valueOf(id));
        ues.setName(user.getName());
        ues.setAddress(user.getAddress());
        ues.setUsername(user.getUsername());
        ues.setPassword(user.getPassword());
        ues.setPhone(user.getPhone());
        service.saveUser(ues);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(user);
    }


}