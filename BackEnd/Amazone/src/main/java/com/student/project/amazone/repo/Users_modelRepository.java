package com.student.project.amazone.repo;

import com.student.project.amazone.entity.Users_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface Users_modelRepository extends JpaRepository<Users_model, Long> {

    @Query(value = "select *from users_model WHERE username =:username and password=:password", nativeQuery = true)
    public Users_model findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    public Users_model findByUsername(String username);

    @Query(value = "SELECT * FROM users_model WHERE id = ?1",nativeQuery = true)
    public Users_model findUserById(Long id);

    Boolean existsByUsername(String username);
}