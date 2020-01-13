package redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.util.RedisLock;
import redis.util.RedisUtil;

/**
 * Redis配置
 *
 * @author David
 */
@Configuration
public class RedisConfig {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil(redisTemplate);
    }

    @Bean
    public RedisLock redisLock() {
        return new RedisLock(redisUtil());
    }
}