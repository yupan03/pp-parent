package com.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信小程序配置
 *
 * @author David
 */
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMiniappProperties {
    /**
     * 设置微信小程序的appid
     */
    private String appId;

    /**
     * 设置微信小程序的app secret
     */
    private String secret;

    private String token;

    private String aesKey;

    private String msgDataFormat;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getMsgDataFormat() {
        return msgDataFormat;
    }

    public void setMsgDataFormat(String msgDataFormat) {
        this.msgDataFormat = msgDataFormat;
    }
}