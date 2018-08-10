package com.example.pojos;

import java.io.Serializable;

public class LoginOutput extends BaseResponse implements Serializable{

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
