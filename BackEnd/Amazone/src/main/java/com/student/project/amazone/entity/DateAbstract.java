package com.student.project.amazone.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class DateAbstract implements Serializable {
    @Temporal(value = TemporalType.DATE)
    @Column(name = "last_updated")
    private Date LastUpdated;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "create_at")
    private Date CreateAt;

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
