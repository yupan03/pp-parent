package redis.util;

import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

public class RedisLock {

    private RedisUtil redisUtil;

    public RedisLock(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 加锁
     *
     * @param lockKey   加锁的Key
     * @param timeStamp 时间戳：当前时间 + 超时时间
     * @return boolean
     */
    public boolean lock(String lockKey, String timeStamp) {
        // 设置过期时间防止死锁
        return redisUtil.setIfAbsent(lockKey, timeStamp, 10, TimeUnit.SECONDS);
    }

    /**
     * 释放锁
     */
    public void release(String lockKey) {
        try {
            String currentValue = redisUtil.get(lockKey);
            if (!StringUtils.isEmpty(currentValue)) {
                // 删除锁状态
                redisUtil.delete(lockKey);
            }
        } catch (Exception e) {
            System.out.println("警报！警报！警报！解锁异常");
        }
    }
}
