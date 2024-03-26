package com.zeng.framework.core;

import com.zeng.framework.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success = true;

    private String message = "操作成功";

    private Integer code = 0;

    private T result;

    private long timestamp = System.currentTimeMillis();

    public static Result<Object> restfull(boolean isOK) {
        return isOK ? ok("操作成功") : bad("操作失败");
    }

    public static Result<Object> restfull(boolean isOK, Object data) {
        return isOK ? success(data) : bad("操作失败");
    }

    /**
    *
    */
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(HttpStatus.OK.value());
        r.setResult(data);
        return r;
    }
    public static Result<Object> ok(String msg) {
        Result<Object> res = success(null);
        res.setMessage(msg);
        return res;
    }

    /**
    *
    */
    public static Result<Object> bad(int code, String msg) {
        Result<Object> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }
    public static Result<Object> bad(Object msg) {
        return bad(HttpStatus.BAD_REQUEST.value(), msg.toString());
    }
    public static Result<Object> error(Object errMsg) {
        log.error("服务器应用出错：" + errMsg.toString());
        return bad(HttpStatus.BAD_REQUEST.value(), "服务器应用出错！！！");
    }
}
