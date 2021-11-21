package com.software.crm.exceptions;

/**
 * @author  :wyanjia
 * @parm    :自定义权限异常
 * @date    : 2021/10/13 16:36
 * @return  :
 * */
public class AuthException extends RuntimeException {
    private Integer code = 400;
    private String msg = "暂无权限!";

    public AuthException() {
        super("暂无权限");
    }

    public AuthException(Integer code) {
        super("暂无权限");
        this.code = code;
    }

    public AuthException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
