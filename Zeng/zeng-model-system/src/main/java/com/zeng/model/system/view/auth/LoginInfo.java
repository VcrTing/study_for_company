package com.zeng.model.system.view.auth;

import com.zeng.model.system.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginInfo {
    public String token;
    public SysUser user;

    public LoginInfo() { }
    public LoginInfo(String token, SysUser user) {
        this.token = token; this.user = user;
    }
}
