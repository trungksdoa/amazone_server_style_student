package com.student.project.amazone.controller;


import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.repo.Users_modelRepository;
import com.student.project.amazone.service.Users_service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class Users_controller {
    private final Users_service service;
    private final Users_modelRepository resposity;

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
            if (service.saveUser(usersModel).isBanned()){
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
    public ResponseEntity<Users_model.userDto> loginUsers(@RequestBody Users_model user) {
        if (service.isLoggedIn(user)) {

            Users_model.userDto userDto = new Users_model.userDto(service.findUserByName(user.getUsername()));
            return ResponseEntity.ok().body(userDto);
        } else {
            throw new IllegalStateException("Login fails");
        }
    }

    @PostMapping("save")
    public ResponseEntity<?> saveUser(@RequestBody Users_model user) {
        if(resposity.existsByUsername(user.getUsername())){
            return new ResponseEntity<>("Lối trùng username", HttpStatus.OK);
        }
        else{
            service.saveUser(user);
        }
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
            return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<Users_model> updateUser(@RequestBody Users_model user) {
        Users_model updateuser = service.updateUser(user);
        return new ResponseEntity<>(updateuser, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Users_model> UpdateUser(@PathVariable("id") String id, @RequestBody Users_model user) {
            user.setId(Long.valueOf(id));
            service.saveUser(service.findUserById(Long.valueOf(id)));
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(user);
    }
}
