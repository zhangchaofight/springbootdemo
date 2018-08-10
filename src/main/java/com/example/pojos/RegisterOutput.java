package com.example.pojos;

import java.io.Serializable;

public class RegisterOutput extends BaseResponse implements Serializable {
    private long userId;

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "RegisterOutput{" +
                "code=" + getCode() + "," +
                "message=" + getMessage() + "," +
                "userId=" + userId + "," +
                '}';
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
