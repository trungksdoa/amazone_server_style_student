package com.student.project.amazone.entity;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "catagory_model")
public class Catagory_model {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "cata_product") // we need to duplicate the physical information
    private Set<Product_model> items;
}
