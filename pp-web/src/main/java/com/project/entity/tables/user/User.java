package com.project.entity.tables.user;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class User implements Serializable {
	private static final long serialVersionUID = 5709541981621536414L;

	private Long id;

	private String uuid;
	private String createTime;
	private String account;
	private String name;
	private String password;
	private String email;

	private boolean enable;

	private Byte type;

	private List<UserRole> roles;

	private List<UserRoleResource> resources;

	@Override
	public String toString() {
		return this.getName() + "  " + this.getId();
	}
}