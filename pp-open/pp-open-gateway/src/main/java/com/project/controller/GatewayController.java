package com.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.model.result.ResultObj;
import common.model.result.StatusEnum;

/**
 * 熔断（服务降级）
 * 
 * @author David
 */
@RestController
public class GatewayController {
	/**
	 ** 熔断降级返回错误信息给前端
	 * 
	 * @return
	 */
	@RequestMapping(value = "/defaultFallback")
	public ResultObj<?> defaultFallback() {
		return new ResultObj<>(StatusEnum.ERROR, "服务器断开连接，请稍后重试！");
	}
}