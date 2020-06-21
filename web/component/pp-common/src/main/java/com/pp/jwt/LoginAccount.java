package com.pp.jwt;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}