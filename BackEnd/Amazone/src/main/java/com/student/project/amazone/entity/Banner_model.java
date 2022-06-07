package com.student.project.amazone.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Banner_model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String imageName;
    private String link;
}
