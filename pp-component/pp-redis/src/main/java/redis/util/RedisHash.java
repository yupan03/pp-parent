package redis.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisHash extends RedisCommon {

	public RedisHash(StringRedisTemplate redisTemplate) {
		super(redisTemplate);
	}

	/**
	 * 获取存储在哈希表中指定字段的值
	 *
	 * @param key
	 * @param field
	 * @return
	 */
	public Object get(String key, String field) {
		return redisTemplate.opsForHash().get(key, field);
	}

	/**
	 * 获取所有给定字段的值
	 *
	 * @param key
	 * @return
	 */
	public Map<Object, Object> entries(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 获取所有给定字段的值
	 *
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<Object> multiGet(String key, Collection<Object> fields) {
		return redisTemplate.opsForHash().multiGet(key, fields);
	}

	public void put(String key, String hashKey, String value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	public void putAll(String key, Map<String, String> maps) {
		redisTemplate.opsForHash().putAll(key, maps);
	}

	/**
	 * 仅当hashKey不存在时才设置
	 *
	 * @param key
	 * @param hashKey
	 * @param value
	 * @return
	 */
	public Boolean putIfAbsent(String key, String hashKey, String value) {
		return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	/**
	 * 删除一个或多个哈希表字段
	 *
	 * @param key
	 * @param fields
	 * @return
	 */
	public Long delete(String key, Object... fields) {
		return redisTemplate.opsForHash().delete(key, fields);
	}

	/**
	 * 查看哈希表 key 中，指定的字段是否存在
	 *
	 * @param key
	 * @param field
	 * @return
	 */
	public boolean hasKey(String key, String field) {
		return redisTemplate.opsForHash().hasKey(key, field);
	}

	/**
	 * 为哈希表 key 中的指定字段的整数值加上增量 increment
	 *
	 * @param key
	 * @param field
	 * @param increment
	 * @return
	 */
	public Long increment(String key, Object field, long increment) {
		return redisTemplate.opsForHash().increment(key, field, increment);
	}

	/**
	 * 为哈希表 key 中的指定字段的整数值加上增量 increment
	 *
	 * @param key
	 * @param field
	 * @param delta
	 * @return
	 */
	public Double increment(String key, Object field, double delta) {
		return redisTemplate.opsForHash().increment(key, field, delta);
	}

	/**
	 * 获取所有哈希表中的字段
	 *
	 * @param key
	 * @return
	 */
	public Set<Object> getKeys(String key) {
		return redisTemplate.opsForHash().keys(key);
	}

	/**
	 * 获取哈希表中字段的数量
	 *
	 * @param key
	 * @return
	 */
	public Long size(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	/**
	 * 获取哈希表中所有值
	 *
	 * @param key
	 * @return
	 */
	public List<Object> values(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	/**
	 * 迭代哈希表中的键值对
	 *
	 * @param key
	 * @param options
	 * @return
	 */
	public Cursor<Map.Entry<Object, Object>> scan(String key, ScanOptions options) {
		return redisTemplate.opsForHash().scan(key, options);
	}

}