package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.Users_model;

import javax.security.auth.login.LoginException;

public interface Users_service {
    public boolean isLoggedIn(Users_model usersModel);
}
