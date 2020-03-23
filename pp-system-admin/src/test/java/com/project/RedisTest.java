package com.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.util.RedisHash;
import redis.util.RedisList;
import redis.util.RedisSet;
import redis.util.RedisString;
import redis.util.RedisZSet;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	private RedisString redisString;
	@Autowired
	private RedisHash redisHash;
	@Autowired
	private RedisList redisList;
	@Autowired
	private RedisSet redisSet;
	@Autowired
	private RedisZSet redisZSet;

	@Test
	public void testString() {
		redisString.set("String", "hello world");
		redisString.set("Stringkey", "yupan", 30, TimeUnit.SECONDS);
		String oldValue = redisString.getAndSet("String", "新值");
		System.out.println(oldValue);
	}

	
	
	/**
	 * 测试Set
	 */
	@Test
	public void testSet() {
		redisSet.add("set", new String[] { "1", "2" });
		String pop = redisSet.pop("set");
		System.out.println(pop);
	}

	/**
	 * 测试有序ZSet(有序集合)
	 */
	@Test
	public void testZset() {
		redisZSet.add("yupan", "12", 1.0);
		redisZSet.add("yupan", "12", 1.0);
		redisZSet.add("yupan", "11", 1.0);

		Set<String> yuan = redisZSet.range("yupan", 0, 10);
		System.out.println(yuan);

		System.out.println("zSet大小" + redisZSet.size("yupan"));
	}

	/**
	 * 测试Hash
	 */
	@Test
	public void testHash() {
		redisHash.put("hash", "yupan", "hello world!");
		Map<Object, Object> hGetAll = redisHash.entries("hash");
		System.out.println(hGetAll);
	}

	/**
	 * 测试List
	 */
	@Test
	public void testList() {
		redisList.leftPushIfPresent("list", "1");
		redisList.leftPushIfPresent("list", "2");
		redisList.leftPushIfPresent("list", "3");
		redisList.leftPushIfPresent("list", "4");

		System.out.println(redisList.range("list", 0, 10));

		System.out.println(redisList.size("list"));
	}
}