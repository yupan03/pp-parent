package com.pp.entity.table;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author David
 * @since 2019-08-02
 */
@Data
public class User {
	@TableId
	private Long id;

	private String account;

	private String name;

	private String password;

	private String email;

	private Integer state;

	private Integer type;

	private String createTime;
}