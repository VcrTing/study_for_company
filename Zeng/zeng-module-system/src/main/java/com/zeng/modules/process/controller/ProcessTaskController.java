package com.zeng.modules.process.controller;

import com.zeng.framework.core.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process/task")
public class ProcessTaskController {


    @GetMapping("/list")
    public Result<Object> list() {

        return Result.ok("OK");
    }
}
