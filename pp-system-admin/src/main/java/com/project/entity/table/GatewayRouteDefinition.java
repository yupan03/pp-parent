package com.project.entity.table;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import com.baomidou.mybatisplus.annotation.TableId;
import com.project.constant.status.State;

import lombok.Data;

// 创建路由模型
@Data
@Validated
public class GatewayRouteDefinition {
	// 路由的Id
	@TableId
	private Integer id;
	// 微服务名称
	@NotEmpty(message = "不能为空")
	private String name;
	// 路由断言集合配置
	private String path;
	// 路由规则转发的目标uri
	private String uri;
	// 状态 0 禁用 1 启用
	private State status;
	// 创建时间
	private String createTime;
}