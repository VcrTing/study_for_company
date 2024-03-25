package com.zeng.model.system.form.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String checkKey;
}
