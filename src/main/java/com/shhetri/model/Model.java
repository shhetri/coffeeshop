package com.shhetri.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Model {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    @PreUpdate
    public void updateTimeStamps() {
        updatedAt = new Date();

        if (createdAt == null && id == 0) {
            createdAt = new Date();
        }
    }
}
