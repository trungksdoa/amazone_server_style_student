package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Order_model;
import com.student.project.amazone.entity.cartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Order_modelRepository extends JpaRepository<Order_model, Long> {
    @Query("FROM Order_model c where c.userId.id = :userId")
    Order_model findByUserId(@Param("userId") Long userId);
}