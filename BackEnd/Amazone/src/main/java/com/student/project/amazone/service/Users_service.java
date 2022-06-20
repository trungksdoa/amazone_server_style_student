package com.student.project.amazone.service;


import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Users_model;

import java.util.List;

public interface Users_service {
    public Users_model isLoggedIn(Users_model usersModel);
    public Users_model isLoggedInAdmin(Users_model usersModel);
    public Users_model saveUser(Users_model user);
    public List<Users_model> findAllUsers();
    public Users_model findUserById(Long id);
    public void deleteUser(Users_model user);
    public Users_model findUserByName(String name);

    public Users_model updateUser(Users_model user);
}
