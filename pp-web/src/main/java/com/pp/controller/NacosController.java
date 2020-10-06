package com.pp.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosController {
    @NacosInjected
    private NamingService namingService;


    public void test() throws Exception {
        namingService.getSubscribeServices();
        namingService.getSubscribeServices();
    }
}