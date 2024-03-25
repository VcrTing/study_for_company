package com.zeng.framework.service;

import com.zeng.framework.component.RedisTool;
import com.zeng.model.system.entity.SysUser;
import com.zeng.utils.sys.UtilToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceToken {

    // 3 小时
    final int EXPIRE_TOKEN = 3 * 60;

    @Autowired
    RedisTool redisTool;

    public String genJwtToken(SysUser sysUser) {
        String key = sysUser.getUsername();
        String token = UtilToken.sign(key, sysUser.getPassword());
        // 存入缓存
        redisTool.setObject(key, token, EXPIRE_TOKEN);

        return token;
    }
}
