package com.example.repositorys;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
public class User implements Serializable{

    //导包不能使用springframework的包  正确的是 import javax.persistence.*;
    @Id
    @Column(name = "user_id", unique = true,nullable=false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long userId;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String userPass;

    private String title;

    private String userIcon;

    private int status;

    public User() {
    }

    public User(String account, String userPass, String title, String userIcon) {
        this.account = account;
        this.userPass = userPass;
        this.title = title;
        this.userIcon = userIcon;
        this.status = 1;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", userPass='" + userPass + '\'' +
                ", title='" + title + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", status=" + status +
                '}';
    }
}
