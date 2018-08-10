package com.example.constant;

import java.io.Serializable;

public final class ErrorCode implements Serializable{

    public static final int SUCCESS = 100;

    //注册相关
    public static final int REGISTER_PARAMS_NOT_NULL = 100100;
    public static final int REGISTER_PARAMS_ACCOUNT_NOT_NULL = 100101;
    public static final int REGISTER_PARAMS_PASS_NOT_NULL = 100102;
    public static final int REGISTER_PARAMS_HAS_REGISTER = 100103;
    public static final int REGISTER_PARAMS_ACCOUNT_ERROR = 100104;
    public static final int REGISTER_PARAMS_PASS_ERROR = 100105;

    //登录相关
    public static final int LOGIN_PARAMS_NOT_NULL = 100200;
    public static final int LOGIN_PARAMS_ACCOUNT_NOT_NULL = 100201;
    public static final int LOGIN_PARAMS_PASS_NOT_NULL = 100202;
    public static final int LOGIN_PARAMS_APPVERSION_NOT_NULL = 100203;
    public static final int LOGIN_PARAMS_DEVICEID_NOT_NULL = 100204;
    public static final int LOGIN_PARAMS_PLATFORM_NULL = 100205;
    public static final int LOGIN_USER_NOT_REGISTER = 100206;
    public static final int LOGIN_WRONG_PASSWORD = 100207;
    public static final int LOGIN_WRONG_STATUS = 100208;

    //欢迎页配置
    public static final int WELCOMEPAGE_APPVERSION_NOT_NULL = 100300;
    public static final int WELCOMEPAGE_APPVERSION_NOT_SUPPORT = 100301;


    //文件上传
    public static final int UPLOAD_FILE_NOT_NULL = 100400;
    public static final int UPLOAD_FILE_FAILED = 100401;
    public static final int UPLOAD_FILE_NAME_SYNTAX_WRONG = 100401;
}
