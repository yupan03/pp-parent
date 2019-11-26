package com.project.constant;

/**
 * 错误业务状态码 从6开头开始，其他几个（1、2、3、4、5）都是http的状态码
 *
 * @author David
 */
public enum BusinessStatus {

    ERROR_SQL(6001, "数据库错误"),

    ERROR_PARAM(6002, "参数错误"),

    LOGIN_FAIL(6003, "登录失败"),

    ;
    public int status;
    public String message;

    BusinessStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }
}