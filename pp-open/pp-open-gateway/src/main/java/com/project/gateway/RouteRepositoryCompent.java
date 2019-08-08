package com.project.gateway;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RouteRepositoryCompent implements RouteDefinitionRepository {

	@Override
	public Flux<RouteDefinition> getRouteDefinitions() {
		System.out.println("获取所有路由");
		return null;
	}

	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		System.out.println("保存路由");
		return Mono.empty();
	}

	@Override
	public Mono<Void> delete(Mono<String> routeId) {
		System.out.println("删除路由");
		return Mono.empty();
	}
}