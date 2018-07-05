package com.example.pojos;

import java.io.Serializable;

public class RegisterOutput extends BaseResponse implements Serializable {
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
