package com.project.entity;

import lombok.Data;

// 创建路由模型
@Data
public class GatewayRouteDefinition {
	// 路由的Id
	private Integer id;
	// 微服务名称
	private String name;
	// 路由断言集合配置
	private String path;
	// 路由规则转发的目标uri
	private String uri;
	// 状态 0 禁用 1 启用
	private Byte status;
	// 创建时间
	private String createTime;
}