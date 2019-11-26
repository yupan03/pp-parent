package com.project.gateway;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.synchronizedMap;

public class RouteRepositoryContext {
    protected static Map<String, RouteDefinition> routes = synchronizedMap(
            new LinkedHashMap<String, RouteDefinition>());
}