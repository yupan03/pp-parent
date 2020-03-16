package redis.util;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

public class RedisZSet extends RedisCommon{

	public RedisZSet(StringRedisTemplate redisTemplate) {
		super(redisTemplate);
	}

	/** ------------------zSet相关操作-------------------------------- */

	/**
	 * 添加元素,有序集合是按照元素的score值由小到大排列
	 *
	 * @param key
	 * @param value
	 * @param score
	 * @return
	 */
	public Boolean zSetAdd(String key, String value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	/**
	 * @param key
	 * @param values
	 * @return
	 */
	public Long zSetAdd(String key, Set<ZSetOperations.TypedTuple<String>> values) {
		return redisTemplate.opsForZSet().add(key, values);
	}

	/**
	 * @param key
	 * @param values
	 * @return
	 */
	public Long zSetRemove(String key, Object... values) {
		return redisTemplate.opsForZSet().remove(key, values);
	}

	/**
	 * 增加元素的score值，并返回增加后的值
	 *
	 * @param key
	 * @param value
	 * @param delta
	 * @return
	 */
	public Double zSetIncrementScore(String key, String value, double delta) {
		return redisTemplate.opsForZSet().incrementScore(key, value, delta);
	}

	/**
	 * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
	 *
	 * @param key
	 * @param value
	 * @return 0表示第一位
	 */
	public Long zSetRank(String key, Object value) {
		return redisTemplate.opsForZSet().rank(key, value);
	}

	/**
	 * 返回元素在集合的排名,按元素的score值由大到小排列
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Long zSetReverseRank(String key, Object value) {
		return redisTemplate.opsForZSet().reverseRank(key, value);
	}

	/**
	 * 获取集合的元素, 从小到大排序
	 *
	 * @param key
	 * @param start 开始位置
	 * @param end   结束位置, -1查询所有
	 * @return
	 */
	public Set<String> zSetRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().range(key, start, end);
	}

	/**
	 * 获取集合元素, 并且把score值也获取
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<String>> zSetRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}

	/**
	 * 根据Score值查询集合元素
	 *
	 * @param key
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public Set<String> zSetRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScore(key, min, max);
	}

	/**
	 * 根据Score值查询集合元素, 从小到大排序
	 *
	 * @param key
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<String>> zSetRangeByScoreWithScores(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
	}

	/**
	 * @param key
	 * @param min
	 * @param max
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<String>> zSetRangeByScoreWithScores(String key, double min, double max,
			long start, long end) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
	}

	/**
	 * 获取集合的元素, 从大到小排序
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zSetReverseRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	/**
	 * 获取集合的元素, 从大到小排序, 并返回score值
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<String>> zSetReverseRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
	}

	/**
	 * 根据Score值查询集合元素, 从大到小排序
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<String> zSetReverseRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
	}

	/**
	 * 根据Score值查询集合元素, 从大到小排序
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<String>> zSetReverseRangeByScoreWithScores(String key, double min,
			double max) {
		return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
	}

	/**
	 * @param key
	 * @param min
	 * @param max
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zSetReverseRangeByScore(String key, double min, double max, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
	}

	/**
	 * 根据score值获取集合元素数量
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zSetCount(String key, double min, double max) {
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	/**
	 * 获取集合大小
	 *
	 * @param key
	 * @return
	 */
	public Long zSetSize(String key) {
		return redisTemplate.opsForZSet().size(key);
	}

	/**
	 * 获取集合大小
	 *
	 * @param key
	 * @return
	 */
	public Long zSetZCard(String key) {
		return redisTemplate.opsForZSet().zCard(key);
	}

	/**
	 * 获取集合中value元素的score值
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Double zSetScore(String key, Object value) {
		return redisTemplate.opsForZSet().score(key, value);
	}

	/**
	 * 移除指定索引位置的成员
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Long zSetRemoveRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().removeRange(key, start, end);
	}

	/**
	 * 根据指定的score值的范围来移除成员
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zSetRemoveRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
	}

	/**
	 * 获取key和otherKey的并集并存储在destKey中
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long zSetUnionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
	}

	/**
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long zSetUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 交集
	 *
	 * @param key
	 * @param otherKey
	 * @param destKey
	 * @return
	 */
	public Long zSetIntersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
	}

	/**
	 * 交集
	 *
	 * @param key
	 * @param otherKeys
	 * @param destKey
	 * @return
	 */
	public Long zSetIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
	}

	/**
	 * @param key
	 * @param options
	 * @return
	 */
	public Cursor<ZSetOperations.TypedTuple<String>> zSetScan(String key, ScanOptions options) {
		return redisTemplate.opsForZSet().scan(key, options);
	}
}