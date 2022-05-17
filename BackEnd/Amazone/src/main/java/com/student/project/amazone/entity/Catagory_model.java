package com.student.project.amazone.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "catagory_model")
@Data
public class Catagory_model {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
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
    @OneToMany
    @JoinColumn(name = "cata_product") // we need to duplicate the physical information
    private Set<Product_model> items;


}
