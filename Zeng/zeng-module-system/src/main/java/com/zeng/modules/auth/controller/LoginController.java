package com.zeng.modules.auth.controller;

import com.zeng.framework.core.Result;
import com.zeng.framework.service.ServiceSysUser;
import com.zeng.framework.service.ServiceToken;
import com.zeng.model.system.entity.SysUser;
import com.zeng.model.system.form.auth.SysLoginForm;
import com.zeng.model.system.util.AuthFormUtil;
import com.zeng.model.system.view.auth.LoginInfo;
import com.zeng.modules.auth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys/auth")
public class LoginController {

    @Autowired
    LoginService service;

    @Autowired
    ServiceToken serviceToken;

    @PostMapping("/login")
    public Result<Object> login(@RequestBody SysLoginForm form) {

        Object jdg = AuthFormUtil.judgeNiceUsername(form);
        if (jdg instanceof String) return Result.bad(jdg);

        // 执行 登录
        Object login = service.login(form);
        if (login instanceof String) return Result.bad(login);
        SysUser user = (SysUser) login;

        // 生成 Token
        String token = serviceToken.genJwtToken(user);
        // 返回
        return Result.ok(new LoginInfo(token, user));
    }
}
