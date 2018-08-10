package com.example.repositorys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="login")
public class Login implements Serializable {

    @Id
    @Column(name = "token", unique = true,nullable=false)
    private String token;

    @Column(nullable = false)
    private long userId;

    public Login() {
    }

    public Login(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
