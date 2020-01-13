package com.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.util.RedisUtil;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void add() {
        // 可以重新设置有效时间
        redisUtil.setDataBase(2);
        redisUtil.set("yupan", "hello world", 40L, TimeUnit.SECONDS);
    }
}