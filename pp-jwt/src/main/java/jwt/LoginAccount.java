package jwt;

import java.util.Date;

import jwt.constant.TokenType;

public class LoginAccount {
    // 账号
    private String username;
    // 登录时间
    private Date loginTime;
    // 类型
    private String type;

    // token类型 0：正常 1：将过期 2：已过期
    private TokenType tokenType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}