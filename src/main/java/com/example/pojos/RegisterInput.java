package com.example.pojos;

import java.io.Serializable;

public class RegisterInput implements Serializable{

    private String account;
    private String userPass;

    public RegisterInput() {
        super();
    }

    public RegisterInput(String account, String userPass) {
        super();
        this.account = account;
        this.userPass = userPass;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public String toString() {
        return "RegisterInput{" +
                "account='" + account + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
