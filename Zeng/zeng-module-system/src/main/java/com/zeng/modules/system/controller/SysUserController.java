package com.zeng.modules.system.controller;

import com.zeng.anno.aop.SwitchRds;
import com.zeng.constant.data.DataSourceConstant;
import com.zeng.system.entity.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    SysUserMapper mapper;

    @GetMapping
    public Object list() {
        return mapper.selectList(null);
    }

    @GetMapping("/2")
    @SwitchRds(dataSource = DataSourceConstant.MULTI_DB_2)
    public Object list2() {
        return mapper.selectList(null);
    }
}
