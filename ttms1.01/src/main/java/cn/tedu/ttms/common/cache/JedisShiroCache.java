package cn.tedu.ttms.common.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import cn.tedu.ttms.common.util.SerializeUtil;

/**
 * 缓存获取Manager
 * @author zhoup
 *
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("unchecked")
public class JedisShiroCache<K, V> implements Cache<K, V>{

	/**
	 * 为了不和其他的缓存混淆，采用追加前缀方式以作区分
	 */
	private static final String REDIS_SHIRO_CACHE = "shiro-demo-cache:";
	/**
	 * Redis 分片(分区)
	 */
	private static final int DB_INDEX = 1;
	 
	private JedisManager jedisManager;
	 
	private String name;
	 
	static final Class<JedisShiroCache> SELF = JedisShiroCache.class;
	 
	public JedisShiroCache(String name, JedisManager jedisManager) {
	    this.name = name;
	    this.jedisManager = jedisManager;
	}
	 
	public String getName() {
		 if (name == null)
	         return "";
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String buildCacheKey(Object key) {
        return REDIS_SHIRO_CACHE + getName() + ":" + key;
    }
	/**
	 * 返回存储在指定key的缓存值
	 * 
	 * @param key 之前添加的键
	 * @return 如果没有指定的key，则返回缓存的对象或null
	 * @throws 如果访问底层缓存系统存在问题，则会抛出CacheException
	 */
	public V get(K key) throws CacheException {
		byte[] byteValue = new byte[0];
		try {
			byte[] byteKey = SerializeUtil.serialize(buildCacheKey(key));
			byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			return (V) SerializeUtil.deserialize(byteValue);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 添加一个缓存条目。
	 * 
	 * @param key 用于标识正在存储的对象
	 * @param value 将值存储在缓存中
	 * @return 如果有先前的值,则返回给定key,否则为null
	 * @throws 如果访问底层缓存系统存在问题，则会抛出CacheException
	 */
	public V put(K key, V value) throws CacheException {
		V previous = get(key);
		try {
			jedisManager.saveValueByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)), 
					SerializeUtil.serialize(value), -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return previous;
	}

	/**
	 * 删除对应于指定键的缓存条目
	 * 
	 * @param key 要删除的条目的键
	 * @return 如果有先前的值,则返回给定key,否则为null
	 * @throws 如果访问底层缓存系统存在问题，则会抛出CacheException
	 */
	public V remove(K key) throws CacheException {
		V previous = get(key);
		try {
			jedisManager.deleteByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return previous;
	}

	/**
	 * 清除缓存中的所有条目
	 * 
	 * @throws 如果访问底层缓存系统存在问题，则会抛出CacheException
	 */
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 返回缓存中的条目数
	 */
	public int size() {
		if (keys() == null)
	        return 0;
	    return keys().size();
	}

	/**
	 * 返回该缓存中包含的所有条目key的视图
	 * 
	 */
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回该缓存中包含的所有value值的视图。
	 * 
	 */
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
