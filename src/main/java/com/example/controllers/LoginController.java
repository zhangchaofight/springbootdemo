package com.example.controllers;

import com.example.constant.ErrorCode;
import com.example.constant.UserConfig;
import com.example.pojos.LoginInput;
import com.example.pojos.LoginOutput;
import com.example.repositorys.User;
import com.example.repositorys.UserRepository;
import com.example.util.ZCTextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录controller
 * 入参：string 设备ID、string 平台类型、string app版本号、string 账户名、string 账户密码
 * 出参：int token（随机int值）
 */
@Controller
public class LoginController {

    private static final int USER_NOT_EXIST = -1;
    private static final int USER_PASS_ERROR = -2;
    private static final int WRONG_USER_STATUS = -3;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public LoginOutput login(LoginInput input) {
        System.out.println(input.toString());

        String account = input.getAccount();
        String userPass = input.getUserPass();
        String platform = input.getPlatform();
        String appVersion = input.getAppVersion();
        String deviceId = input.getDeviceId();

        LoginOutput output = new LoginOutput();
        if (ZCTextUtils.isEmpty(account) && ZCTextUtils.isEmpty(userPass)) {
            output.setCode(ErrorCode.LOGIN_PARAMS_NOT_NULL);

        } else if (ZCTextUtils.isEmpty(account)) {
            output.setCode(ErrorCode.LOGIN_PARAMS_ACCOUNT_NOT_NULL);

        } else if (ZCTextUtils.isEmpty(userPass)) {
            output.setCode(ErrorCode.LOGIN_PARAMS_PASS_NOT_NULL);

        } else if (ZCTextUtils.isEmpty(deviceId)) {
            output.setCode(ErrorCode.LOGIN_PARAMS_DEVICEID_NOT_NULL);

        } else if (ZCTextUtils.isEmpty(platform)) {
            output.setCode(ErrorCode.LOGIN_PARAMS_PLATFORM_NULL);

        } else if (ZCTextUtils.isEmpty(appVersion)) {
            output.setCode(ErrorCode.LOGIN_PARAMS_APPVERSION_NOT_NULL);

        } else {
            long id = getUserId(account, userPass);
            if (id == USER_NOT_EXIST) {
                output.setCode(ErrorCode.LOGIN_USER_NOT_REGISTER);

            } else if (id == USER_PASS_ERROR) {
                output.setCode(ErrorCode.LOGIN_WRONG_PASSWORD);

            } else if (id == WRONG_USER_STATUS) {
                output.setCode(ErrorCode.LOGIN_WRONG_STATUS);

            } else {
                output.setCode(ErrorCode.SUCCESS);
                output.setToken(getToken(id));
                output.setMessage("登陆成功");
            }
        }
        return output;
    }

    /**
     * 获取用户userId
     * @param account 账号
     * @param userPass 密码
     * @return 用户userId 如果大于等0正常  -1 用户不存在 -2 密码错误 -3
     */
    private long getUserId(String account, String userPass) {
        User user = userRepository.findByAccount(account);
        if (user == null) {
            return USER_NOT_EXIST;
        } else if (user.getStatus() == UserConfig.USER_NORMAL &&
                !ZCTextUtils.equals(userPass, user.getUserPass())) {
            return USER_PASS_ERROR;
        } else if (user.getStatus() != UserConfig.USER_NORMAL) {
            return WRONG_USER_STATUS;
        }
        return user.getUserId();
    }

    /**
     * 获取用户token
     * @param userId 用户Id
     * @return 返回token
     */
    private int getToken(long userId) {
        return 1234567890;
    }
}
