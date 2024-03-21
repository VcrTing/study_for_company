package org.jeecg.zeng.form.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.zeng.form.service.FormCommonServiceImpl;
import org.jeecg.zeng.model.entity.form.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/form/common")
public class FormCommonController {

    @Autowired
    FormCommonServiceImpl formCommonService;

    @ApiOperation("单个自定义报表获取")
    @GetMapping(value = "/schema")
    // @SwitchMasterRds
    public Result<?> getSchema(@RequestParam("formId") Long formId) {
        Form form = formCommonService.getById(formId);
        return Result.ok(form);
    }
}
