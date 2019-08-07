package com.project.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.entity.GatewayRouteDefinition;
import com.project.service.DynamicRouteServiceImpl;

@RestController
@RequestMapping("/route")
public class RouteController {
	@Autowired
	private DynamicRouteServiceImpl dynamicRouteService;

	@PostConstruct
	public void init() {
		List<GatewayRouteDefinition> gatewayRouteDefinitions = new ArrayList<>();

		GatewayRouteDefinition temp = new GatewayRouteDefinition();

		temp.setName("pp-open-pay");
		temp.setPath("/pay/**");
		temp.setUri("lb://pp-open-pay");
		temp.setStatus((byte) 1);

		gatewayRouteDefinitions.add(temp);

		if (gatewayRouteDefinitions != null) {
			List<GatewayRouteDefinition> routeDefinitions = gatewayRouteDefinitions.stream()
					.filter(a -> a.getStatus().equals((byte) 1)).collect(Collectors.toList());
			for (GatewayRouteDefinition gatewayRouteDefinition : routeDefinitions) {
				RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
				System.out.println(definition.getId());
				this.dynamicRouteService.add(definition);
			}
		}
	}

	// 增加路由
	@PostMapping("/add")
	public boolean add(@RequestBody GatewayRouteDefinition gwdefinition) {
		try {
			RouteDefinition definition = assembleRouteDefinition(gwdefinition);
			return this.dynamicRouteService.add(definition);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 更新路由
	@PostMapping("/update")
	public boolean update(@RequestBody GatewayRouteDefinition gwdefinition) {
		RouteDefinition definition = assembleRouteDefinition(gwdefinition);
		return this.dynamicRouteService.update(definition);
	}

	// 删除路由
	@PostMapping("/delete")
	public boolean delete(String id) {
		return this.dynamicRouteService.delete(id);
	}

	// 把传递进来的参数转换成路由对象
	private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition) {
		RouteDefinition definition = new RouteDefinition();
		definition.setId(gwdefinition.getName());
		definition.setOrder(0);

		// 设置默认断言
		List<PredicateDefinition> pdList = new ArrayList<>();
		PredicateDefinition predicate = new PredicateDefinition();
		Map<String, String> predicateArgs = new HashMap<>();
		predicateArgs.put("name", gwdefinition.getName());// 将serviceId放入断言中
		predicateArgs.put("pattern", gwdefinition.getPath());
		predicate.setArgs(predicateArgs);
		predicate.setName("Path");
		pdList.add(predicate);
		definition.setPredicates(pdList);

		URI uri = null;
		if (gwdefinition.getUri().startsWith("http")) {
			uri = UriComponentsBuilder.fromHttpUrl(gwdefinition.getUri()).build().toUri();
		} else {
			// uri为 lb://consumer-service 时使用下面的方法
			uri = URI.create(gwdefinition.getUri());

			// 设置默认过滤器
			List<FilterDefinition> filters = new ArrayList<>();
			// 去除前缀
			FilterDefinition filter = new FilterDefinition();
			filter.setName("StripPrefix");
			Map<String, String> filterArgs = new HashMap<>();
			filterArgs.put("_genkey_0", "1");
			filter.setArgs(filterArgs);
			filters.add(filter);

			// 熔断
			FilterDefinition fallbackFilter = new FilterDefinition();
			fallbackFilter.setName("Hystrix");
			Map<String, String> fallbackFilterArgs = new HashMap<>();
			fallbackFilterArgs.put("name", "fallbackcmd");
			fallbackFilterArgs.put("fallbackUri", "forward:/defaultFallback");
			fallbackFilter.setArgs(fallbackFilterArgs);
			filters.add(fallbackFilter);

			definition.setFilters(filters);
		}
		definition.setUri(uri);
		return definition;
	}
}