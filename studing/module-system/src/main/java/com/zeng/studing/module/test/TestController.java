package com.zeng.studing.module.test;

import com.zeng.studing.system.component.RocketMqTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    // @Autowired
    // RocketMqTool rocketMqTool;

    @GetMapping
    public Object doing() {
        System.out.println("进来了");
        // rocketMqTool.test();
        return "";
    }
}
