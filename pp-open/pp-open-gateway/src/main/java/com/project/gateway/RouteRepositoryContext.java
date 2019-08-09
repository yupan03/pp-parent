package com.project.gateway;

import static java.util.Collections.synchronizedMap;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.cloud.gateway.route.RouteDefinition;

public class RouteRepositoryContext {
	protected static Map<String, RouteDefinition> routes = synchronizedMap(new LinkedHashMap<String, RouteDefinition>());
}