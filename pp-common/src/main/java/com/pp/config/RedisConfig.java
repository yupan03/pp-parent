package com.pp.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * 自定义redis配置（多库模式）
 * 每个库建立一个连接
 */
@Configuration
public class RedisConfig {
    @Resource
    private RedisProperties redisProperties;

    private LettuceConnectionFactory lettuceConnectionFactory(int database) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        configuration.setDatabase(database);
        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());
        configuration.setPassword(RedisPassword.of(redisProperties.getPassword()));

        LettuceClientConfiguration.LettuceClientConfigurationBuilder configurationBuilder = LettuceClientConfiguration.builder();

        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, configurationBuilder.build());

        factory.afterPropertiesSet();

        return factory;
    }

    @Bean("database(0)")
    public StringRedisTemplate getStringRedisTemplateDatabase0() {
        return new StringRedisTemplate(lettuceConnectionFactory(0));
    }

    @Bean("database(1)")
    public StringRedisTemplate getStringRedisTemplateDatabase1() {
        return new StringRedisTemplate(lettuceConnectionFactory(1));
    }
}