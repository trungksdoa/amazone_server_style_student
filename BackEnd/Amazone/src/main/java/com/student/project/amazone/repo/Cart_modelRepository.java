package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Cart_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Cart_modelRepository extends JpaRepository<Cart_model, Long> {

    @Query("FROM Cart_model c where c.UserId.id = :userId")
    Cart_model findCart_modelByUserId(@Param("userId") Long userId);

}