package cn.tedu.ttms.common.cache;

import org.apache.shiro.cache.Cache;

/**
 * shiro cache manager 接口
 * @author zhoup
 *
 */
public interface ShiroCacheManager {
	
	 <K, V> Cache<K, V> getCache(String name);

	 void destroy();
}
