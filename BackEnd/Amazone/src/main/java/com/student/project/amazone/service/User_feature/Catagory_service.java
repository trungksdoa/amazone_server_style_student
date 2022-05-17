package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Product_model;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Catagory_service {
    public Catagory_model saveCatagory(Catagory_model user);
    public Catagory_model updateCategory(Catagory_model user);

    public List<Catagory_model> findAllCategory();

    public Catagory_model findUserById(Long id);

    public void deleteCatagory(Long id);

    public Catagory_model findUserByName(String name);
}
