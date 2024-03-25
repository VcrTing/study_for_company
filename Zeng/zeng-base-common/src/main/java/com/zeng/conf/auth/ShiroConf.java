package com.zeng.conf.auth;


import com.zeng.framework.component.auth.SysUserRealm;
import com.zeng.framework.constant.RouterConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConf {

    @Autowired
    SysUserRealm userRealm;

    /**
     * 创建 manager
     * @params
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        // init manager
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 创建加密对象，设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 采用 md5 加密
        matcher.setHashAlgorithmName("md5");
        // 加密次数
        matcher.setHashIterations(3);
        // 将加密对象存储到 realm 之中
        userRealm.setCredentialsMatcher(matcher);
        // realm 存入到 manager 之中
        manager.setRealm(userRealm);
        System.out.println("SHIRO MANAGER SUCCESS");
        // 返回
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

        Map<String, String> map = new HashMap<>();
        // 白名单
        Arrays.stream(RouterConstant.WHITE_LIST).forEach(s -> map.put(s, "anon"));
        // 拦截
        // map.put("/sys/**", "authc");
        // map.put("/**", "authc");

        bean.setFilterChainDefinitionMap(map);
        bean.setLoginUrl("/sys/auth/login");
        return bean;
    }
}
