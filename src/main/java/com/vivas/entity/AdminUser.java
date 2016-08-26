package com.vivas.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by duyot on 8/25/2016.
 */
@Entity
@Table(name = "ADMIN_USER")
@javax.persistence.SequenceGenerator(
        name="sequence",
        sequenceName="SEQ_ADMIN_USER"
)
public class AdminUser {

    private Long id;
    private String username;
    private String password;

    public AdminUser() {
    }


    public AdminUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(generator = "sequence")
    @Column(name = "USER_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "USER_NAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
