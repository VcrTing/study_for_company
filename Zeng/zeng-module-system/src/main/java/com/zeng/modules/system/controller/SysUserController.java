package com.zeng.modules.system.controller;

import com.zeng.model.system.entity.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    SysUserMapper mapper;

    @GetMapping("/list")
    public Object list() {
        return mapper.selectList(null);
    }

}
