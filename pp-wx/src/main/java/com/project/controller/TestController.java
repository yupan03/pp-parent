package com.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "测试接口")
@RestController
public class TestController {

    @ApiOperation(value = "第一个接口", notes = "测试接口")
    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }
}