package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Users_model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users_modelRepository extends JpaRepository<Users_model, Long> {
    public Users_model findByNameAndPassword(String name, String password);
}