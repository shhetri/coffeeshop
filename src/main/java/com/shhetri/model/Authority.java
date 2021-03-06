package com.shhetri.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = {"authority", "user_id"}))
public class Authority extends Model {
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private Person person;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    public Authority(Person user, String authority) {
        this.person = user;
        this.authority = authority;
    }

    public Authority() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person user) {
        this.person = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
