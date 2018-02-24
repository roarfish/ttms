package cn.tedu.ttms.common.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import cn.tedu.ttms.common.dao.CustomShiroSessionDAO;
import cn.tedu.ttms.common.util.StringUtil;
import cn.tedu.ttms.system.dao.SysUserDao;
import cn.tedu.ttms.system.entity.SysUser;
import cn.tedu.ttms.system.entity.SysUserOnline;

/**
 * 用户Session 手动管理
 * @author zhoup
 *
 */
public class CustomSessionManager {
	/**
	 * session status 
	 */
	public static final String SESSION_STATUS ="sojson-online-status";
	
	ShiroSessionRepository shiroSessionRepository;
	
	CustomShiroSessionDAO customShiroSessionDAO;

	@Resource
	private SysUserDao sysUserDao;
	
	public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}

	public void setCustomShiroSessionDAO(CustomShiroSessionDAO customShiroSessionDAO) {
		this.customShiroSessionDAO = customShiroSessionDAO;
	}

	/**
	 * 获取所有的有效Session用户
	 * @return
	 */
	public List<SysUserOnline> getAllUser() {
		// 获取所有session
		Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
		List<SysUserOnline> list = new ArrayList<SysUserOnline>();
		
		for (Session session : sessions) {
			SysUserOnline on = getSessionOnline(session);
			if (null != on) {
				list.add(on);
			}
		}
		return list;
	}
	
	/**
	 * 根据ID查询 SimplePrincipalCollection
	 * @param userIds	用户ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(Long...userIds){
		// 把userIds 转成Set，好判断
		Set<Long> idset = (Set<Long>) StringUtil.array2Set(userIds);
		// 获取所有session
		Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
		// 定义返回
		List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
		for (Session session : sessions) {
			// 获取SimplePrincipalCollection
			Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if (null != obj && obj instanceof SimplePrincipalCollection) {
				// 强转
				SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
				// 判断用户，匹配用户ID
				obj = spc.getPrimaryPrincipal();
				if (null != obj && obj instanceof SysUser) {
					SysUser user = (SysUser) obj;
					// 比较用户ID,符合即加入集合
					if (null != user && idset.contains(user.getId())) {
						list.add(spc);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取单个Session
	 * @param sessionId
	 * @return
	 */
	public SysUserOnline getSession(String sessionId){
		Session session = shiroSessionRepository.getSession(sessionId);
		SysUserOnline on = getSessionOnline(session);
		return on;
	}
	
	public SysUserOnline getSessionOnline(Session session){
		// 获取session登录信息
		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		//SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		if (null == obj) {
			return null;
		}
		// 确保是 SimplePrincipalCollection对象。
		if (obj instanceof SimplePrincipalCollection) {
			SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
			obj = spc.getPrimaryPrincipal();
			SysUser user =sysUserDao.findObjectByName((String) obj);
			if (null != obj /*&& obj instanceof SysUser*/) {
				// 存储session + user 综合信息
				SysUserOnline userOnline = new SysUserOnline(user);
				// 最后一次和系统交互的时间
				userOnline.setLastAccess(session.getLastAccessTime());
				// 主机的ip地址
				userOnline.setHost(session.getHost());
				// Session ID
				userOnline.setSessionId(session.getId().toString());
				// session最后一次登录的时间
				userOnline.setLastLoginTime(session.getLastAccessTime());
				// 回话到期 ttl(ms)
				userOnline.setTimeout(session.getTimeout());
				// session创建时间
				userOnline.setStartTime(session.getStartTimestamp());
				// 是否退出
				SessionStatus sessionStatus = (SessionStatus) session.getAttribute(SESSION_STATUS);
				boolean status = Boolean.TRUE;
				if (null != sessionStatus) {
					status =  sessionStatus.getOnlineStatus();
				}
				userOnline.setSessionStatus(status);
				return userOnline;
			}
		}
		return null;
	}
	
	/**
	 * 改变Session状态
	 * @param status {true:踢出,false:激活}
	 * @param sessionId
	 * @return
	 */
	public Map<String, Object> changeSessionStatus(Boolean status, String sessionIds) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String[] sessionIdArray = null;
			if (sessionIds.indexOf(",") == -1) {
				sessionIdArray = new String[]{sessionIds};
			}else{
				sessionIdArray = sessionIds.split(",");
			}
			for (String id : sessionIdArray) {
				Session session = shiroSessionRepository.getSession(id);
				SessionStatus sessionStatus = new SessionStatus();
				sessionStatus.setOnlineStatus(status);
				session.setAttribute(SESSION_STATUS, sessionStatus);
				customShiroSessionDAO.update(session);
			}
			map.put("status", 200);
			map.put("sessionStatus", status ? 1 : 0);
			map.put("sessionStatusText", status ? "踢出" : "激活");
			map.put("sessionStatusTextTd", status ? "有效" : "已踢出");
		} catch (Exception e) {
			map.put("status", 500);
			map.put("message", "改变失败，有可能Session不存在，请刷新再试！");
		}
		return map;
	}
	
	/**
	 * 查询要禁用的用户是否在线。
	 * @param id		用户ID
	 * @param status	用户状态
	 */
	public void forbidUserById(Long id,Long status){
		// 获取所有在线用户
		for (SysUserOnline on : getAllUser()) {
			Integer userId = on.getId();
			// 匹配用户ID
			if (userId.equals(id)) {
				// 获取用户Session
				Session session = shiroSessionRepository.getSession(on.getId());
				// 标记用户Session
				SessionStatus sessionStatus = (SessionStatus) session.getAttribute(SESSION_STATUS);
				// 是否踢出 true: 有效，false：踢出
				sessionStatus.setOnlineStatus(status.intValue() == 1);
				// 更新Session
				customShiroSessionDAO.update(session);
			}
		}
	}
}
