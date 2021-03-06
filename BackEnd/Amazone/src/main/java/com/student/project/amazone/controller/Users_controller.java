package com.student.project.amazone.controller;


import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.service.Users_service;
import com.sun.jersey.api.ConflictException;
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
            if (service.updateOrSave(usersModel).isBanned()) {
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
        HttpStatus status = HttpStatus.OK;
        System.out.println("Ok");
        try {
            Users_model.userDto userDto = new Users_model.userDto(service.isLoggedIn(user));
            respone.put("user", userDto);
            respone.put("message", "????ng nh???p th??nh c??ng, xin ch??o " + userDto.getUsername());
        } catch (NullPointerException ex) {
            respone.put("message", ex.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(status).body(respone);
    }

    @PostMapping("save")
    public ResponseEntity<Map<Object,Object>> saveUser(@RequestBody Users_model user) {
        HttpStatus status = HttpStatus.OK;
        System.out.println("Ok");
        try {
            Users_model.userDto userDto = new Users_model.userDto(service.registerUser(user));
            respone.put("user", userDto);
            respone.put("message", "????ng ky th??nh c??ng, xin ch??o " + userDto.getUsername());
        } catch (Exception ex) {
            ex.printStackTrace();
            respone.put("message", ex.getMessage());
            status = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(status).body(respone);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Users_model> UpdateUser(@PathVariable String id, @RequestBody Users_model user) {
        if (service.findUserById(Long.valueOf(id)) != null) {
            user.setId(Long.valueOf(id));
            service.updateOrSave(service.findUserByName(user.getUsername()));
        }

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(user);
    }
}
