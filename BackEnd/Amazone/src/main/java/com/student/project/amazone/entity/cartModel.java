package com.student.project.amazone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.student.project.amazone.entity.cartItem;
import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "cart_model")
@Data
@EqualsAndHashCode(callSuper = true)
public class cartModel extends DateAbstract  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JsonProperty("cartItem")
    private List<cartItem> cartItem = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "FK_user_id")
    @JsonProperty("userId")
    private Users_model userId;


    public cartModel(Long userId) {
        super();
        Users_model user = new Users_model();
        user.setId(userId);
        this.userId = user;
    }
    public cartModel() {
    }


}
