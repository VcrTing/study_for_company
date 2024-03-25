package com.zeng.modules.auth.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeng.framework.service.ServiceSysUser;
import com.zeng.model.system.entity.SysUser;
import com.zeng.model.system.entity.mapper.SysUserMapper;
import com.zeng.model.system.form.auth.SysLoginForm;
import com.zeng.utils.sys.UtilPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {

    @Autowired
    ServiceSysUser sysUser;

    public Object login(SysLoginForm form) {
        String name = form.getUsername();
        String pwd = form.getPassword();

        SysUser user = sysUser.userByName(name);
        if (user == null) return "未找到该用户";

        String inputPwd = UtilPassword.encrypt(name, pwd, user.getSalt());
        if (inputPwd == null) return "输入密码生成加密密码错误";

        log.info("密码是否相同 = " + inputPwd.equals(user.getPassword()) + "输入的密码 = " + inputPwd + " ; 用户的密码 = " + user.getPassword());
        if (inputPwd.equals(user.getPassword())) {

            // 添加一条系统日志
            // 用户登录成功
            return user;
        }

        return "账户的密码错误";
    }
}
