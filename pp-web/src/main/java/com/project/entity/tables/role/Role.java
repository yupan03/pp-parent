package com.project.entity.tables.role;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Role {
	@TableId
	private String id;
	private String name;
	private String createTime;
}