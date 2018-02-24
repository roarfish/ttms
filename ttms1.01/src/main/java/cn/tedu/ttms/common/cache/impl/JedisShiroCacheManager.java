package cn.tedu.ttms.common.cache.impl;

import org.apache.shiro.cache.Cache;

import cn.tedu.ttms.common.cache.JedisManager;
import cn.tedu.ttms.common.cache.JedisShiroCache;
import cn.tedu.ttms.common.cache.ShiroCacheManager;

/**
 * JRedis管理
 * @author zhoup
 *
 */
public class JedisShiroCacheManager implements ShiroCacheManager{
	
	private JedisManager jedisManager;

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

	
	public <K, V> Cache<K, V> getCache(String name) {
		return new JedisShiroCache<K,V>(name, getJedisManager());
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
	
		
	}
	
	
}
