package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.repo.Users_modelRepository;
import com.sun.jersey.api.ConflictException;
import com.sun.jersey.api.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
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
    public boolean isLoggedIn(Users_model usersModel) {
        Users_model users_model = service.findByNameAndPassword(usersModel.getName(), usersModel.getPassword());
        if (users_model != null) return true;
        throw new NotFoundException("Account not found");
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
    public Users_model findUserByName(String name) {
        return service.findByName(name);
    }
}
