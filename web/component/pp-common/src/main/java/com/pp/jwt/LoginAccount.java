package com.pp.jwt;

import lombok.Data;

@Data
public class LoginAccount {
    private Long id;
    // 账号
    private String username;
    // 登录时间
    private String loginTime;
    // 登录类型
    private byte type;

    // token类型 0：正常 1：将过期 2：已过期
    private TokenType tokenType;
}