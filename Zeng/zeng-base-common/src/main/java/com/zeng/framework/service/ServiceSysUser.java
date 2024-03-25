package com.zeng.framework.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zeng.model.system.entity.SysUser;
import com.zeng.model.system.entity.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ServiceSysUser {

    @Autowired
    SysUserMapper mapper;

    public SysUser userByName(String name) {
        if (!StringUtils.hasLength(name)) return null;
        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.lambda().eq(SysUser::getUsername, name);
        return mapper.selectOne(qw);
    }

}
