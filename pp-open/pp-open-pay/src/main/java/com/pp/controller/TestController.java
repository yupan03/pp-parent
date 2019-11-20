package com.pp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试")
@RestController
public class TestController {

    @ApiOperation(value = "测试接口")
    @GetMapping(value = "test")
    public String test() {
        return "hello world!";
    }
}