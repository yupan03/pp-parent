package com.pp.redis.lock;

import com.pp.redis.RedisString;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

public class RedisLock {

	private RedisString redisString;

	public RedisLock(RedisString redisString) {
		this.redisString = redisString;
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
		return redisString.setIfAbsent(lockKey, timeStamp, 30, TimeUnit.SECONDS);
	}

	/**
	 * 释放锁
	 */
	public void release(String lockKey) {
		try {
			String currentValue = redisString.get(lockKey);
			if (!StringUtils.isEmpty(currentValue)) {
				// 删除锁状态
				redisString.delete(lockKey);
			}
		} catch (Exception e) {
			System.out.println("警报！警报！警报！解锁异常");
		}
	}
}
