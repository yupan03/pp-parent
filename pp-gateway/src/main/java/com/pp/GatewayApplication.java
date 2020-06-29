package com.pp;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.pp.service.DynamicRouteService;
import com.pp.service.SentinelService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GatewayApplication.class);

        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

    @Resource
    private DynamicRouteService dynamicRouteService;
    @Resource
    private SentinelService sentinelService;

    @Override
    public void run(String... args) {
        // 动态路由
        dynamicRouteService.dyRoutes();
        // 动态api分组管理
        sentinelService.apiDefinitionHandler();
        // 动态限流
        sentinelService.gatewayRulesHandler();
    }
}