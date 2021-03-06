package cn.tedu.ttms.common.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;

import cn.tedu.ttms.common.util.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * redis连接器
 * @author zhoup
 *
 */
public class JedisManager {
	
	private JedisPool jedisPool;
	
	// 获取连接
	public Jedis getJedis(){
		Jedis jedis = null;
		try {
			jedis = getJedisPool().getResource();
		} catch (JedisConnectionException e) {
			String message =StringUtils.trim(e.getMessage());
			if("Could not get a resource from the pool".equalsIgnoreCase(message)){
        		System.out.println("++++++++++请检查你的redis服务++++++++");
        		System.out.println("|①.请检查是否安装redis服务");
        		System.out.println("|②.请检查redis 服务是否启动。启动口诀[安装目录中的redis-server.exe，双击即可，如果有错误，请用CMD方式启动，怎么启动百度]|");
        		System.out.println("|③.请检查redis启动是否带配置文件启动，也就是是否有密码，是否端口有变化（默认6379）。解决方案，参考第二点。如果需要配置密码和改变端口，请修改spring-cache.xml配置。|");
        		
        		System.exit(0);//停止项目
        	}
			throw new JedisConnectionException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return jedis;
	}
	
	// 获取连接池
	public JedisPool getJedisPool(){
		return jedisPool;
	}
	
	public void setJedisPool(JedisPool jedisPool){
		this.jedisPool = jedisPool;
	}
	
	// 归还连接
	public void returnResource(Jedis jedis, boolean isBroken){
		if(jedis == null)
			return;
		
		jedis.close();
	}
	
	// 在redis中根据key获取value值
	public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
		Jedis jedis = null;
		byte[] result = null;
		boolean isBroken = false;
		try {
			// 获取连接
			jedis = getJedis();
			// 选择进行操作的redis数据库
			jedis.select(dbIndex);
			// 获取value结果
			result = jedis.get(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		}finally{
			returnResource(jedis, isBroken);
		}
		return result;
	}
	
	// 在redis中根据key删除值
	public void deleteByKey(int dbIndex, byte[] key)  throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			@SuppressWarnings("unused")
			Long result = jedis.del(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		}finally {
			returnResource(jedis, isBroken);
		}
	}
	
	// 保存对应的key与value值
	public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)  throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.set(key, value);
			if(expireTime > 0){
				// 对给定的key设置过期时间
                jedis.expire(key, expireTime);
			}
		} catch (Exception e) {
			isBroken = true;
			throw e;
		}finally {
			returnResource(jedis, isBroken);
		}
	}
	
	// 获取所有Session
	@SuppressWarnings("unchecked")
	public Collection<Session> allSession(int dbIndex, String redisShiroSession) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		Set<Session> sessions = new HashSet<Session>();
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			
			Set<byte[]> byteKeys = jedis.keys((JedisShiroSessionRepository.REDIS_SHIRO_ALL).getBytes());
			if (byteKeys != null && byteKeys.size() > 0) {
				for (byte[] bs : byteKeys) {
					Session obj = SerializeUtil.deserialize(jedis.get(bs), Session.class);
					if (obj instanceof Session) {
						sessions.add(obj);
					}
				}
			}
		} catch (Exception e) {
			isBroken =true;
			throw e;
		}finally {
			returnResource(jedis, isBroken);
		}
		return sessions;
	}
}
