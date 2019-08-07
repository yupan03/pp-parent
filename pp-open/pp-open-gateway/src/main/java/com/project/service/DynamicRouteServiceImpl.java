package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;

	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	// 增加路由
	public boolean add(RouteDefinition definition) {
		routeDefinitionWriter.save(Mono.just(definition)).subscribe();
		publisher.publishEvent(new RefreshRoutesEvent(this));

		return true;
	}

	// 更新路由
	public boolean update(RouteDefinition definition) {
		try {
			delete(definition.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			routeDefinitionWriter.save(Mono.just(definition)).subscribe();
			publisher.publishEvent(new RefreshRoutesEvent(this));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除路由
	public boolean delete(String id) {
		try {
			routeDefinitionWriter.delete(Mono.just(id)).subscribe();
			publisher.publishEvent(new RefreshRoutesEvent(this));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}