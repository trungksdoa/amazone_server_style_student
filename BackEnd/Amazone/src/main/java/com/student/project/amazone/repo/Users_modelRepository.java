package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Users_model;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users_modelRepository extends JpaRepository<Users_model, Long> {
    public Users_model findByUsernameAndPassword(String username, String password);
    public Users_model findByUsername(String username);
    @Query("SELECT * FROM users_model WHERE id = ?1")
    public Users_model findUserById(Long id);
}