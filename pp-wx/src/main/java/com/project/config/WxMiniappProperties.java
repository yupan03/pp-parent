package com.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 ** 微信小程序配置
 * 
 * @author David
 */
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMiniappProperties {
	/**
	 ** 设置微信小程序的appid
	 */
	private String appId;

	/**
	 ** 设置微信小程序的app secret
	 */
	private String secret;

	private String token;

	private String aesKey;

	private String msgDataFormat;
}