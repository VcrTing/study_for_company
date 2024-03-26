package com.zeng.modules.form.controller;

import com.zeng.framework.anno.SwitchMasterRds;
import com.zeng.framework.core.Result;
import com.zeng.model.form.entity.FormSchema;
import com.zeng.modules.form.service.FormSchemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/form/common")
public class FormSchemaController {

    @Autowired
    FormSchemaService service;

    @PostMapping(value = "/addSchema")
    @SwitchMasterRds
    public Result<?> addSchema(@RequestBody FormSchema form) {
        try {
            return Result.restfull(service.save(form), form.getFormId());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteSchema")
    @SwitchMasterRds
    public Result<?> deleteSchema(@RequestParam("formId") Long formId) {
        try {
            return Result.restfull(service.removeById(formId));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping(value = "/updateSchema")
    @SwitchMasterRds
    public Result<?> updateSchema(@RequestBody FormSchema form) {
        try {
            return Result.restfull(service.updateById(form));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping(value = "/schema")
    @SwitchMasterRds
    public Result<FormSchema> getSchema(@RequestParam("formId") Long formId) {
        return Result.success(service.getById(formId));
    }

    @GetMapping(value = "/allSchemas")
    @SwitchMasterRds
    public Result<List<FormSchema>> getAllSchemas() {
        return Result.success(service.list());
    }

}
