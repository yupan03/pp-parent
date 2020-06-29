package com.pp.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * nacos动态路由处理
 */
@Service
public class DynamicRouteService {
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;
    @Autowired
    private DynamicRouteHandler dynamicRouteHandler;

    public void dyRoutes() {
        final String dataId = "gateway-routes";

        final String group = "DEFAULT_GROUP";

        try {
            Properties properties = new Properties();

            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);

            // nacos动态路由监听
            ConfigService configService = NacosFactory.createConfigService(properties);

            String config = configService.getConfig(dataId, group, 1000);

            dyRoutes(config);

            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    dyRoutes(configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    private void dyRoutes(String config) {
        dynamicRouteHandler.clearRoute();
        dynamicRouteHandler.clearRoute();
        try {
            List<RouteDefinition> gatewayRouteDefinitions = JSONObject.parseArray(config, RouteDefinition.class);
            for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
                dynamicRouteHandler.addRoute(routeDefinition);
            }
            dynamicRouteHandler.publish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}