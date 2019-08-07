package common.model;

import lombok.Data;

@Data
public class LoginUser {
	// 登录账号
	private String account;
	private String type;
	// 登录时间
	private String loginTime;
}