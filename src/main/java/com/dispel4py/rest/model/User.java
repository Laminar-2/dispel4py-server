package com.dispel4py.rest.model;

import javax.persistence.*;

/**
 * Model Class to represent User
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    Integer userId;
    @Column
    String userName;
    @Column
    String password;

    public User(Integer userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userId) {
        this.userName = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer id) {
        this.userId = id;
    }

    @Override
    public String toString() {
        return "User: " + getUserName();
    }

}
