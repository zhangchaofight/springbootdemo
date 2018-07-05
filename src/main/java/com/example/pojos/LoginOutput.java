package com.example.pojos;

import java.io.Serializable;

public class LoginOutput extends BaseResponse implements Serializable{

    private int token;

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}
