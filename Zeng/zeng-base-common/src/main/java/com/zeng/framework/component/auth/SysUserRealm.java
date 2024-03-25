package com.zeng.framework.component.auth;

import com.zeng.framework.service.ServiceSysUser;
import com.zeng.model.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SysUserRealm extends AuthorizingRealm {

    @Autowired
    ServiceSysUser sysUser;

    /**
     * 自定义授权
     * @params
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 自定义登录认证
     * @params
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        // 获取用户信息
        String name = authenticationToken.getPrincipal().toString();

        // 业务层获取数据库用户
        SysUser user = sysUser.userByName(name);

        // 非空判断，完成数据封装返回
        if (user != null) {
            return new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(), // user
                    user.getPassword(), // pass
                    ByteSource.Util.bytes(user.getSalt()), // 盐
                    name // realm name
            );
        }

        // 空
        return null;
    }
}
