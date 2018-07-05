package com.example.pojos;

public class LoginInput {
    private String account;
    private String userPass;
    private String deviceId;
    private String appVersion;
    private String platform;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "LoginInput{" +
                "account='" + account + '\'' +
                ", userPass='" + userPass + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
