package redis.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisSet extends RedisBase {

	public RedisSet(StringRedisTemplate redisTemplate) {
		super(redisTemplate);
	}

	/**
	 * set添加元素
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public Long add(String key, String... values) {
		return redisTemplate.opsForSet().add(key, values);
	}

	/**
	 * set移除元素
	 *
	 * @param key
	 * @param values
	 * @return
	 */
	public Long remove(String key, Object... values) {
		return redisTemplate.opsForSet().remove(key, values);
	}

	/**
	 * 移除并返回集合的一个随机元素
	 *
	 * @param key
	 * @return
	 */
	public String pop(String key) {
		return redisTemplate.opsForSet().pop(key);
	}

	/**
	 * 将元素value从一个集合移到另一个集合
	 *
	 * @param key
	 * @param value
	 * @param destKey
	 * @return
	 */
	public Boolean move(String key, String value, String destKey) {
		return redisTemplate.opsForSet().move(key, value, destKey);
	}

	/**
	 * 获取集合的大小
	 *
	 * @param key
	 * @return
	 */
	public Long size(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	/**
	 * 判断集合是否包含value
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean isMember(String key, Object value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	/**
	 * 获取两个集合的交集
	 *
	 * @param key
	 * @param otherKey
	 * @return
	 */
	public Set<String> intersect(String key, String otherKey) {
		return redisTemplate.opsForSet().intersect(key, otherKey);
	}

	/**
	 * 获取key集合与多个集合的交集
	 *
	 * @param key
	 * @param otherKeys
	 * @return
	 */
	public Set<String> intersect(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().intersect(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的交集存储到destKey集合中
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long intersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
	}

	/**
	 * key集合与多个集合的交集存储到destKey集合中
	 *
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
	}

	/**
	 * 获取两个集合的并集
	 *
	 * @param key
	 * @param otherKeys
	 * @return
	 */
	public Set<String> union(String key, String otherKeys) {
		return redisTemplate.opsForSet().union(key, otherKeys);
	}

	/**
	 * 获取key集合与多个集合的并集
	 *
	 * @param key
	 * @param otherKeys
	 * @return
	 */
	public Set<String> union(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().union(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的并集存储到destKey中
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long unionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
	}

	/**
	 * key集合与多个集合的并集存储到destKey中
	 *
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long unionAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 获取两个集合的差集
	 *
	 * @param key
	 * @param otherKey
	 * @return
	 */
	public Set<String> difference(String key, String otherKey) {
		return redisTemplate.opsForSet().difference(key, otherKey);
	}

	/**
	 * 获取key集合与多个集合的差集
	 *
	 * @param key
	 * @param otherKeys
	 * @return
	 */
	public Set<String> difference(String key, Collection<String> otherKeys) {
		return redisTemplate.opsForSet().difference(key, otherKeys);
	}

	/**
	 * key集合与otherKey集合的差集存储到destKey中
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long differenceAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
	}

	/**
	 * key集合与多个集合的差集存储到destKey中
	 *
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long differenceAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
	}

	/**
	 * 获取集合所有元素
	 *
	 * @param key
	 * @return
	 */
	public Set<String> members(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 随机获取集合中的一个元素
	 *
	 * @param key
	 * @return
	 */
	public String randomMember(String key) {
		return redisTemplate.opsForSet().randomMember(key);
	}

	/**
	 * 随机获取集合中count个元素
	 *
	 * @param key
	 * @param count
	 * @return
	 */
	public List<String> randomMembers(String key, long count) {
		return redisTemplate.opsForSet().randomMembers(key, count);
	}

	/**
	 * 随机获取集合中count个元素并且去除重复的
	 *
	 * @param key
	 * @param count
	 * @return
	 */
	public Set<String> distinctRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().distinctRandomMembers(key, count);
	}

	/**
	 * @param key
	 * @param options
	 * @return
	 */
	public Cursor<String> scan(String key, ScanOptions options) {
		return redisTemplate.opsForSet().scan(key, options);
	}

}