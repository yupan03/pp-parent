package com.project.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.QueryPage;
import com.project.entity.table.GatewayRouteDefinition;
import com.project.service.impl.GetwayServiceImpl;

import common.result.ResultObj;
import common.result.ResultPage;
import common.result.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "路由管理配置")
@RestController
@RequestMapping(value = "/getway")
@Validated
public class GetwayController {

    @Autowired
    private GetwayServiceImpl getwayService;

    @ApiOperation(value = "新增路由")
    @PostMapping(value = "/add")
    public ResultObj<Integer> add(@Valid @RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        return ResultUtils.success(getwayService.add(gatewayRouteDefinition));
    }

    @ApiOperation(value = "编辑路由")
    @PostMapping(value = "/update")
    public ResultObj<Integer> update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        return ResultUtils.success(getwayService.update(gatewayRouteDefinition));
    }

    @ApiOperation(value = "删除路由")
    @PostMapping(value = "/delete")
    public ResultObj<Integer> delete(@NotNull(message = "不能为空") @RequestBody(required = false) Integer id) {
        return ResultUtils.success(getwayService.delete(id));
    }

    @ApiOperation(value = "getway服务获取所有路由信息")
    @PostMapping(value = "/getAll")
    public ResultPage<GatewayRouteDefinition> getAll(@RequestBody QueryPage<GatewayRouteDefinition> page) {
        getwayService.getAll(page);
        return page.getResultPage();
    }
}