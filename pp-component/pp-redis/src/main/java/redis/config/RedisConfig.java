package redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.util.RedisLock;
import redis.util.RedisUtil;

import java.time.Duration;

/**
 * Redis配置多数据库索引
 * 切换不同数据索引是，业务状态改变，可能会造成数据错乱的情况
 *
 * @author David
 */
@Configuration
public class RedisConfig {
    // Redis服务器地址
    @Value("${spring.redis.host}")
    private String host;
    // Redis服务器连接端口
    @Value("${spring.redis.port}")
    private Integer port;
    // Redis数据库索引（默认为0）
    @Value("${spring.redis.database}")
    private Integer database;
    // Redis服务器连接密码（默认为空）
    @Value("${spring.redis.password}")
    private String password;
    // 连接超时时间（毫秒）
    @Value("${spring.redis.timeout}")
    private Integer timeout;
    // 连接池最大连接数（使用负值表示没有限制）
    @Value("${spring.redis.lettuce.pool.max-active}")
    private Integer maxTotal;
    // 连接池最大阻塞等待时间（使用负值表示没有限制）
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private Integer maxWait;
    // 连接池中的最大空闲连接
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private Integer maxIdle;
    // 连接池中的最小空闲连接
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private Integer minIdle;
    // 关闭超时时间
    @Value("${spring.redis.lettuce.shutdown-timeout}")
    private Integer shutdown;

    /**
     * 获取缓存连接池
     *
     * @return
     */
    @Bean
    public LettucePoolingClientConfiguration getPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWait);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        LettucePoolingClientConfiguration pool = LettucePoolingClientConfiguration.builder()
                .poolConfig(config)
                .commandTimeout(Duration.ofMillis(timeout))
                .shutdownTimeout(Duration.ofMillis(shutdown))
                .build();
        return pool;
    }

    /**
     * 多例Bean
     *
     * @return
     */
    @Bean
    @Scope(value = "prototype")
    public LettuceConnectionFactory jedisConnectionFactory() {

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setDatabase(database);
        configuration.setPassword(RedisPassword.of(password));

        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    @Scope(value = "prototype")
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        return new StringRedisTemplate(lettuceConnectionFactory);
    }

    @Bean
    @Scope(value = "prototype")
    public RedisUtil redisUtil(StringRedisTemplate stringRedisTemplate) {
        return new RedisUtil(stringRedisTemplate);
    }

    @Bean
    @Scope(value = "prototype")
    public RedisLock redisLock(RedisUtil redisUtil) {
        return new RedisLock(redisUtil);
    }
}