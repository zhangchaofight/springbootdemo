package com.example.controllers;

import com.example.constant.ErrorCode;
import com.example.pojos.BaseResponse;
import com.example.pojos.WelcomePageInput;
import com.example.pojos.WelcomePageOutput;
import com.example.util.ZCTextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/config")
public class ConfigController {

    private static final int APP_VERSION_SUPPORT = 0;
    private static final int APP_VERSION_NOTNULL = 1;
    private static final int APP_VERSION_NOT_SUPPORT = 2;


    @RequestMapping(value = "/welcomepage")
    public BaseResponse getWelcomePage(WelcomePageInput input) {
        BaseResponse response = new BaseResponse();
        switch (validateAppVersion(input.getAppVersion())){
            case APP_VERSION_NOTNULL:
                response.setCode(ErrorCode.WELCOMEPAGE_APPVERSION_NOT_NULL);
                break;
            case APP_VERSION_NOT_SUPPORT:
                response.setCode(ErrorCode.WELCOMEPAGE_APPVERSION_NOT_SUPPORT);
                break;
            default:
                response.setCode(ErrorCode.SUCCESS);
                response.setData(getWelcomeOutput());
                break;
        }
        return response;
    }

    private int validateAppVersion(String appVersion) {
        if (ZCTextUtils.isEmpty(appVersion)) {
            return APP_VERSION_NOTNULL;
        } else {
            return APP_VERSION_SUPPORT;
        }
    }

    private WelcomePageOutput getWelcomeOutput() {
        WelcomePageOutput output = new WelcomePageOutput();
        return output;
    }
}
