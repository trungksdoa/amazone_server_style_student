package com.student.project.amazone.repo;

import com.student.project.amazone.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemDtoRepository extends JpaRepository<CartItem, Long> {
}