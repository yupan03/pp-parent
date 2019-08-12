package com.project.gateway;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.entity.GatewayRouteDefinition;

import reactor.core.publisher.Mono;

@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    @Autowired
    private RouteRepositoryCompent routeDefinitionRepository;

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void refreshRout() {
        // 清空内存中的路由信息
        RouteRepositoryContext.routes.clear();

        // 刷新路由，每次都从数据库中获取
        List<GatewayRouteDefinition> gatewayRouteDefinitions = new ArrayList<>();

        GatewayRouteDefinition temp = new GatewayRouteDefinition();

        temp.setName("pp-web");
        temp.setPath("/web/**");
        temp.setUri("lb://pp-web");
        temp.setStatus((byte) 1);

        gatewayRouteDefinitions.add(temp);

        if (gatewayRouteDefinitions != null) {
            List<GatewayRouteDefinition> routeDefinitions = gatewayRouteDefinitions.stream()
                    .filter(a -> a.getStatus().equals((byte) 1)).collect(Collectors.toList());
            for (GatewayRouteDefinition gatewayRouteDefinition : routeDefinitions) {
                RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
                System.out.println(definition.getId());
                routeDefinitionRepository.save(Mono.just(definition)).subscribe();
            }
        }

        publisher.publishEvent(new RefreshRoutesEvent(this));
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
            fallbackFilterArgs.put("fallbackUri", "forward:/defaultFallback?serviceId" + gwdefinition.getName());// 熔断降级
            fallbackFilter.setArgs(fallbackFilterArgs);
            filters.add(fallbackFilter);

            definition.setFilters(filters);
        }

        definition.setUri(uri);

        return definition;
    }
}