package com.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.model.result.Result;
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
	@RequestMapping(value = "/defaultFallback", method = RequestMethod.GET)
	public Result defaultFallback(String serviceId) {
		return new Result(StatusEnum.ERROR, "微服务(" + serviceId + ")断开连接，请稍后重试！");
	}
}