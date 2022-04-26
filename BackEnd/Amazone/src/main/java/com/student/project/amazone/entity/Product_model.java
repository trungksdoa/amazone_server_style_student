package com.student.project.amazone.entity;


import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "product_model")
@Data
public class Product_model {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "cata_product", insertable = false, updatable = false)
    private Catagory_model catagory;
}
