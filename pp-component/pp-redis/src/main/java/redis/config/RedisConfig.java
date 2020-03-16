package redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.util.RedisCommon;
import redis.util.RedisHash;
import redis.util.RedisList;
import redis.util.RedisLock;
import redis.util.RedisSet;
import redis.util.RedisString;
import redis.util.RedisZSet;

@Configuration
public class RedisConfig {
	@Bean
	public RedisCommon RedisCommon(StringRedisTemplate redisTemplate) {
		return new RedisCommon(redisTemplate);
	}

	@Bean
	public RedisString redisString(StringRedisTemplate redisTemplate) {
		return new RedisString(redisTemplate);
	}

	@Bean
	public RedisHash redisHash(StringRedisTemplate redisTemplate) {
		return new RedisHash(redisTemplate);
	}

	@Bean
	public RedisList redisList(StringRedisTemplate redisTemplate) {
		return new RedisList(redisTemplate);
	}

	@Bean
	public RedisSet redisSet(StringRedisTemplate redisTemplate) {
		return new RedisSet(redisTemplate);
	}

	@Bean
	public RedisZSet redisZSet(StringRedisTemplate redisTemplate) {
		return new RedisZSet(redisTemplate);
	}

	@Bean
	public RedisLock redisLock(RedisString redisString) {
		return new RedisLock(redisString);
	}
}