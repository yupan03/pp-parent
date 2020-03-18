package com.project.entity.dao.table;

import lombok.Data;

/**
 * @author David
 * @since 2019-08-02
 */
@Data
public class User {

	private Long id;

	private String account;

	private String name;

	private String password;

	private String email;

	private Integer state;

	private Integer type;

	private String createTime;
}