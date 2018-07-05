package com.example.controllers;

import com.example.constant.ErrorCode;
import com.example.pojos.RegisterInput;
import com.example.pojos.RegisterOutput;
import com.example.repositorys.User;
import com.example.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public @ResponseBody RegisterOutput register(RegisterInput input) {

        if (input != null) {
            System.out.println(input.toString());
        }

        RegisterOutput output = new RegisterOutput();
        if (input == null || (input.getAccount() == null || input.getAccount().length() == 0)
                && (input.getUserPass() == null || input.getUserPass().length() == 0)) {
            output.setCode(ErrorCode.REGISTER_PARAMS_NOT_NULL);
            output.setMessage("请求数据不能为空");
        } else if (input.getAccount() == null || input.getAccount().length() == 0) {
            output.setCode(ErrorCode.REGISTER_PARAMS_ACCOUNT_NOT_NULL);
            output.setMessage("用户名不能为空");
        } else if (input.getUserPass() == null || input.getUserPass().length() == 0) {
            output.setCode(ErrorCode.REGISTER_PARAMS_PASS_NOT_NULL);
            output.setMessage("密码不能为空");
        } else {
            if (hasRegistered(input.getAccount())) {
                output.setCode(ErrorCode.REGISTER_PARAMS_HAS_REGISTER);
                output.setMessage("该账号已注册请登录");
            } else {
                if (!validateAccount()) {
                    output.setCode(ErrorCode.REGISTER_PARAMS_ACCOUNT_ERROR);
                    output.setMessage("账号格式有误");
                } else if (!validatePassword()) {
                    output.setCode(ErrorCode.REGISTER_PARAMS_PASS_ERROR);
                    output.setMessage("密码格式有误");
                } else {
                    User user = userRepository.save(new User(input.getAccount(), input.getUserPass(), null, null));
                    output.setCode(ErrorCode.SUCCESS);
                    output.setMessage("创建成功");
                    output.setUserId(user.getUserId());
                }
            }
        }
        return output;
    }

    private boolean hasRegistered(String account) {
        List<User> users = userRepository.findAllByAccount(account);
        if (users != null && users.size() > 0) {
            for (User user : users) {
                if (user.getStatus() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validateAccount() {
        return true;
    }

    private boolean validatePassword() {
        return true;
    }
}
