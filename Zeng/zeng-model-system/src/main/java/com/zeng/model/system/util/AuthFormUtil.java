package com.zeng.model.system.util;

import com.zeng.model.system.form.auth.SysLoginForm;
import org.springframework.util.StringUtils;

public final class AuthFormUtil {

    public static Object judgeNiceUsername(SysLoginForm form) {
        if (form != null) {
            String name = form.getUsername();
            if (StringUtils.hasLength(name)) {
                return true;
            }
            return "用户名输入错误";
        }
        return "没有表单数据";
    }
}
