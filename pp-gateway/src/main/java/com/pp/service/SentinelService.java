package com.pp.service;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;

@Service
public class SentinelService {
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;

    /**
     * 动态API分组管理
     */
    public void apiDefinitionHandler() {
        final String dataId = "gateway-sentinel-api";

        final String group = "DEFAULT_GROUP";

        try {
            Properties properties = new Properties();

            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);

            // nacos动态路由监听
            ConfigService configService = NacosFactory.createConfigService(properties);

            String config = configService.getConfig(dataId, group, 1000);
            apiDefinitionHandler(config);

            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    apiDefinitionHandler(configInfo);
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

    private void apiDefinitionHandler(String config) {
        Set<ApiDefinition> apiDefinitions = new HashSet<>();
        JSONArray array = JSON.parseArray(config);
        for (Object obj : array) {
            JSONObject o = (JSONObject) obj;
            ApiDefinition apiDefinition = new ApiDefinition((o.getString("apiName")));
            Set<ApiPredicateItem> predicateItems = new HashSet<>();
            JSONArray itemArray = o.getJSONArray("predicateItems");
            if (itemArray != null) {
                predicateItems.addAll(itemArray.toJavaList(ApiPathPredicateItem.class));
            }
            apiDefinition.setPredicateItems(predicateItems);
            apiDefinitions.add(apiDefinition);
        }
        GatewayApiDefinitionManager.loadApiDefinitions(new HashSet<>(apiDefinitions));
    }


    /**
     * 动态
     */
    public void gatewayRulesHandler() {
        final String dataId = "gateway-sentinel-rules";

        final String group = "DEFAULT_GROUP";

        try {
            Properties properties = new Properties();

            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);

            // nacos动态路由监听
            ConfigService configService = NacosFactory.createConfigService(properties);

            String config = configService.getConfig(dataId, group, 1000);
            gatewayRulesHandler(config);

            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    gatewayRulesHandler(configInfo);
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

    private void gatewayRulesHandler(String config) {
        List<GatewayFlowRule> gatewayFlowRules = JSONObject.parseArray(config, GatewayFlowRule.class);
        if (gatewayFlowRules != null) {
            GatewayRuleManager.loadRules(new HashSet<>(gatewayFlowRules));
        }
    }
}