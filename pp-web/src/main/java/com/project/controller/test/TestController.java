package com.project.controller.test;

import com.project.entity.RequestParam;
import com.project.entity.tables.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "测试")
@RestController
@RequestMapping(value = "/")
public class TestController {

    @ApiOperation(value = "/sayHello")
    @PostMapping(value = "/sayHello")
    public Map<String, Object> getMethodName(@RequestBody RequestParam<User> requestParam) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "hello world!");
        System.out.println(requestParam.getObj().getName());
        map.put("requestParam", requestParam);
        return map;
    }
}