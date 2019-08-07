package com.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.entity.table.GatewayRouteDefinition;

@FeignClient(value = "pp-gateway")
public interface GatewayFeign {

	/**
	 * 增加路由
	 * 
	 * @param gwdefinition
	 * @return
	 */
	@PostMapping("/route/add")
	boolean add(@RequestBody GatewayRouteDefinition gwdefinition);

	/**
	 * 更新路由
	 * 
	 * @param gwdefinition
	 * @return
	 */
	@PostMapping("/route/update")
	boolean update(@RequestBody GatewayRouteDefinition gwdefinition);

	/**
	 * 删除路由
	 * 
	 * @param name 路由服务名称
	 * @return
	 */
	@PostMapping("/route/delete")
	boolean delete(@RequestParam("id") String name);
}