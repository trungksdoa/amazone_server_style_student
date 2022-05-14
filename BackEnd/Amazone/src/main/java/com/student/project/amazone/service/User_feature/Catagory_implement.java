package com.student.project.amazone.service.User_feature;


import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.repo.Catagory_modelRespository;
import com.sun.jersey.api.ConflictException;
import com.sun.jersey.api.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class Catagory_implement implements Catagory_service{
    private final Catagory_modelRespository service;

    public Catagory_implement(Catagory_modelRespository service) {
        this.service = service;
    }
    @Override
    public Catagory_model saveCatagory(Catagory_model user){
        if (service.findByName(user.getName()) == null)
            return service.save(user);
        throw new ConflictException("Catagory already exists");
    }

    @Override
    public List<Catagory_model> findAllCategory(){
        return service.findAll();
    }
    @Override
    public Catagory_model findUserById(Long id){
        if (service.findCatagoryById(id) != null) return service.findCatagoryById(id);
        throw new NotFoundException("Catagory not exists");
    }
    @Override
    public void deleteCatagory(Catagory_model user){
        if (service.findCatagoryById(user.getId()) != null) service.delete(user);
        throw new NotFoundException("Catagory not exists");
    }
    @Override
    public Catagory_model findUserByName(String name){
        return service.findByName(name);
    }


}
