package com.student.project.amazone.service;

import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.repo.Users_modelRepository;
import com.sun.jersey.api.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class Users_implement implements Users_service {
    public final Users_modelRepository service;

    public Users_implement(Users_modelRepository service) {
        this.service = service;
    }


    @Override
    public Users_model isLoggedIn(Users_model usersModel) {
        Users_model users_model = service.findUserByUsernameAndPassword(usersModel.getUsername(), usersModel.getPassword());
        if (users_model != null) {
            return users_model;
        } else {
            throw new NullPointerException("Không tìm thấy tài khoản " + usersModel.getUsername());
        }
    }

    @Override
    public Users_model isLoggedInAdmin(Users_model usersModel) {
        Users_model users_model = service.findUserByUsernameAndPasswordAndIsAdmin(usersModel.getUsername(), usersModel.getPassword());
        if (users_model != null) {
            return users_model;
        } else {
            throw new NullPointerException("Không tìm thấy tài khoản " + usersModel.getUsername());
        }
    }

    @Override
    public Users_model saveUser(Users_model user) {
        return service.save(user);
    }

    @Override
    public List<Users_model> findAllUsers() {
        return service.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Users_model findUserById(Long id) {
        if (service.findUserById(id) != null)
            return service.findUserById(id);
        throw new NotFoundException("User not exists");
    }

    @Override
    public void deleteUser(Users_model user) {
        service.delete(user);
    }

    @Override
    public Users_model findUserByName(String username) {
        if (service.findByUsername(username) != null)
            return service.findByUsername(username);
        throw new NotFoundException("Không tìm thấy tài khoản " + username);
    }
}
