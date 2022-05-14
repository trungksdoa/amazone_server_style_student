package com.student.project.amazone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "cart_model")
@Data
public class Cart_model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JsonProperty("cartItem")
    private List<CartItem> cartItem = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "FK_user_id")
    @JsonProperty("userId")
    private Users_model UserId;

    @Temporal(value = TemporalType.DATE)
    private Date LastUpdated;

    @Temporal(value = TemporalType.DATE)
    private Date CreateAt;

    @JsonProperty("itemActive")
    private Boolean active;

    public Cart_model(Boolean actives, Long userId) {
        Users_model user = new Users_model();

        user.setId(userId);
        this.active = actives;
        this.UserId = user;
    }

    public Cart_model() {

    }


    @PrePersist
    public void prePersist() {
        if (CreateAt == null) {
            CreateAt = new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        LastUpdated = new Date();
    }
}
