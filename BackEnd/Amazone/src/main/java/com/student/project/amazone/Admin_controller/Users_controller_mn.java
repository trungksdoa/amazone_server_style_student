package com.student.project.amazone.Admin_controller;


import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.entity.cartModel;
import com.student.project.amazone.service.Users_service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9111")
public class Users_controller_mn {
    private final Users_service service;

    Map<Object, Object> respone = new HashMap<>();
    cartModel cartData = new cartModel();

    @GetMapping
    public ResponseEntity<List<Users_model>> getUsers() {
        return ResponseEntity.ok().body(service.findAllUsers());
    }


    @GetMapping("isAdmin")
    public ResponseEntity<Boolean> checkIsAdmin(@RequestParam String name) {
        Users_model usersModel = service.findUserByName(name);
        if (usersModel != null) {
            if (usersModel.isAdmin()) {
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.ok().body(false);
    }

    @PatchMapping("banUser")
    public ResponseEntity<Boolean> bannedUser(@RequestParam String name) {
        Users_model usersModel = service.findUserByName(name);
        if (usersModel != null) {
            usersModel.setBanned(true);
            if (service.saveUser(usersModel).isBanned()){
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.ok().body(false);
    }

    @GetMapping("isDeleted")
    public ResponseEntity<Boolean> checkIsDeleted(@RequestParam String name) {
        Users_model usersModel = service.findUserByName(name);
        if (usersModel != null) {
            if (usersModel.isDeleted()) {
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.ok().body(false);
    }

    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> loginUsers(@RequestBody Users_model user) {
        HttpStatus status = HttpStatus.OK;
        try {
            Users_model.userDto userDto = new Users_model.userDto(service.isLoggedInAdmin(user));
            respone.put("user", userDto);
            respone.put("message", "Đăng nhập thành công, xin chào " + userDto.getUsername());
        } catch (Exception ex) {
            respone.put("message", ex.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(status).body(respone);
    }

    @PostMapping("save")
    public ResponseEntity<Users_model> saveUser(@RequestBody Users_model user) {
        service.saveUser(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Users_model> UpdateUser(@PathVariable String id, @RequestBody Users_model user) {
        if (service.findUserById(Long.valueOf(id)) != null) {
            user.setId(Long.valueOf(id));
            service.saveUser(service.findUserByName(user.getName()));
        }

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(user);
    }
}
