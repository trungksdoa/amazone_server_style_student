package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Catagory_model;
import com.student.project.amazone.entity.Product_model;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Catagory_modelRespository extends JpaRepository<Catagory_model,Long> {
    Catagory_model findByName(String name);

    @Query(value = "SELECT * FROM catagory_model WHERE id = ?1",
            nativeQuery = true)
    public Catagory_model findCatagoryById(Long id);

    @Query(value = "SELECT * FROM catagory_model", nativeQuery = true)
    public List<Catagory_model> findAllCatagorys();
}
