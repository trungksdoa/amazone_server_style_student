package com.student.project.amazone.entity;


import lombok.Data;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "product_model")
@Data

public class Product_model {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String imageurl;
    private Long price;
    @Column(name = "created_at")
    @Temporal(value = TemporalType.DATE)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(value = TemporalType.DATE)
    private Date updatedAt;
    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new Date();

    }
    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }
    @PreRemove
    protected void preRemove() {
        this.updatedAt = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "cata_product", insertable = false, updatable = false)
    private Catagory_model catagory;

    public Product_model(String name, Long price, Catagory_model catagory) {
        this.name = name;
        this.price = price;
        this.catagory = catagory;
    }

    public Product_model() {

    }
}
