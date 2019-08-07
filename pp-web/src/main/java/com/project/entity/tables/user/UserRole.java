package com.project.entity.tables.user;

import lombok.Data;

@Data
public class UserRole {
	private Long id;

	private String userUuid;
	private String userName;
	private String roleUuid;
	private String roleName;
	private String createTime;
}