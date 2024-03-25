package com.zeng.modules.system;

import com.zeng.framework.anno.SwitchRds;
import com.zeng.framework.constant.data.DataSourceConstant;
import com.zeng.model.system.entity.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public Object test() {
        return "TEST OK /source/1 默认数据源";
    }

    @Autowired
    SysUserMapper mapper;

    @GetMapping("/users")
    @SwitchRds(dataSource = DataSourceConstant.MASTER)
    public Object listUsers() {
        return mapper.selectList(null);
    }

    @GetMapping("/source/1")
    @SwitchRds(dataSource = DataSourceConstant.MULTI_DB_1)
    public Object list() {
        return mapper.selectList(null);
    }

    @GetMapping("/source/2")
    @SwitchRds(dataSource = DataSourceConstant.MULTI_DB_2)
    public Object list2() {
        return mapper.selectList(null);
    }
}
