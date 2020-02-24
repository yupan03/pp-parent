package redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.util.RedisLock;
import redis.util.RedisUtil;

@Configuration
public class RedisConfig {
    @Bean
    public RedisUtil redisUtil(StringRedisTemplate stringRedisTemplate) {
        return new RedisUtil(stringRedisTemplate);
    }

    @Bean
    public RedisLock redisLock(RedisUtil redisUtil) {
        return new RedisLock(redisUtil);
    }
}