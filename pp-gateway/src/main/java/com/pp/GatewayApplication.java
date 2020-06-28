package com.pp;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.pp.service.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GatewayApplication.class);

        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

    private final String dataId = "gateway-routes";

    private final String group = "DEFAULT_GROUP";

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;
    @Autowired
    private DynamicRouteService dynamicRouteService;

    @Override
    public void run(String... args) throws Exception {
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
        dynamicRouteService.clearRoute();
        dynamicRouteService.clearRoute();
        try {
            List<RouteDefinition> gatewayRouteDefinitions = JSONObject.parseArray(config, RouteDefinition.class);
            for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
                dynamicRouteService.addRoute(routeDefinition);
            }
            dynamicRouteService.publish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}