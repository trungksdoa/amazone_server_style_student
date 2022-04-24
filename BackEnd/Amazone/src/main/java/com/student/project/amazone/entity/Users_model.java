package com.student.project.amazone.entity;

import java.lang.String;
import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "users_model")
public class Users_model {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String name;
    private String password;
    private String address;
    private String phone;
}
