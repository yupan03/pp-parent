package com.pp.controller.test;

import com.pp.entity.RequestParam;
import com.pp.entity.tables.user.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class TestController {

    @PostMapping(value = "/sayHello")
    public Map<String, Object> getMethodName(@RequestBody RequestParam<User> requestParam) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "hello world!");
        System.out.println(requestParam.getObj().getName());
        map.put("requestParam", requestParam);
        return map;
    }
}