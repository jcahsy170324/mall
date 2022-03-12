package com.msb.dongbao.common.base.enums;



public enum StateCodeEnum {
    /**
     *用户名密码不正确
     **/
    PASSWORD_ERROR(1001,"用户名密码不正确"),

    /**
     * 用户不存在
     */
    USER_EMPTY(1002,"用户不存在"),

    /**
     *请求成功
     **/
    SUCCESS(200,"请求成功"),

    /**
     *请求失败
     **/
    ERROR(500,"请求失败");


    private int code;
    private String msg;

    StateCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
