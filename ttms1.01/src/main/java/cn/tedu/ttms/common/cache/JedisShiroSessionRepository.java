package cn.tedu.ttms.common.cache;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

import cn.tedu.ttms.common.session.CustomSessionManager;
import cn.tedu.ttms.common.session.SessionStatus;
import cn.tedu.ttms.common.session.ShiroSessionRepository;
import cn.tedu.ttms.common.util.SerializeUtil;

@SuppressWarnings("unchecked")
public class JedisShiroSessionRepository implements ShiroSessionRepository{
	
	public static final String REDIS_SHIRO_SESSION = "sojson-shiro-demo-session:";
	public static final String REDIS_SHIRO_ALL = "*sojson-shiro-demo-session:*";
	private static final int SESSION_VAL_TIME_SPAN = 18000;
	private static final int DB_INDEX = 1;
	    
	private JedisManager jedisManager;
	
	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

	private String buildRedisSessionKey(Serializable sessionId) {
	    return REDIS_SHIRO_SESSION + sessionId;
	}
	 
	
	public void saveSession(Session session) {
		if (session == null || session.getId() == null)
			throw new NullPointerException("session is empty");
		try {
			byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));
			
			//不存在才能添加
			if (null == session.getAttribute(CustomSessionManager.SESSION_STATUS)) {
				// Session 踢出
				SessionStatus sessionStatus = new SessionStatus();
				session.setAttribute(CustomSessionManager.SESSION_STATUS,sessionStatus);
			}
			byte[] value = SerializeUtil.serialize(session);
			getJedisManager().saveValueByKey(DB_INDEX, key, value, (int) (session.getTimeout() / 1000));
		} catch (Exception e) {
			throw new RuntimeException("save session error");
		}
	}

	
	public void deleteSession(Serializable id) {
		if (id == null) {
			throw new NullPointerException("session id is empty");
		}
		try {
			getJedisManager().deleteByKey(DB_INDEX, SerializeUtil.serialize(buildRedisSessionKey(id)));
		} catch (Exception e) {
			throw new RuntimeException("删除session出现异常");
		}
	}

	
	public Session getSession(Serializable sessionId) {
		if (sessionId == null)
			throw new NullPointerException("session id is empty");
		Session session = null;
		try {
			byte[] value = getJedisManager().getValueByKey(DB_INDEX, SerializeUtil.serialize(buildRedisSessionKey(sessionId)));
			session = SerializeUtil.deserialize(value,Session.class);
		} catch (Exception e) {
			throw new RuntimeException("获取session异常");
		}
		return session;
	}

	
	public Collection<Session> getAllSessions() {
		Collection<Session> sessions = null;
		try {
			sessions = getJedisManager().allSession(DB_INDEX, REDIS_SHIRO_SESSION);
		} catch (Exception e) {
			throw new RuntimeException("获取全部session异常");
		}
		return sessions;
	}
}
