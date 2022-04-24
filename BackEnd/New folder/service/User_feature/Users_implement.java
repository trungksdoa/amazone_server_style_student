package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.Users_model;
import com.student.project.amazone.repo.Users_modelRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class Users_implement implements Users_service {
    public final Users_modelRepository service;

    public Users_implement(Users_modelRepository service) {
        this.service = service;
    }

    @Override
    public boolean isLoggedIn(Users_model usersModel) {
        Users_model users_model = service.findByNameAndPassword(usersModel.getName(), usersModel.getPassword());
        if (users_model != null) {
            return true;
        }
        throw new SecurityException("Account not found");
    }
}
