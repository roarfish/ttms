package cn.tedu.ttms.system.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 在线用户
 * @author zhoup
 *
 */
public class SysUserOnline extends SysUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7216249095502675438L;
	
	// Session Id
	private String sessionId;
	// Session Host
	private String host;
	// Session创建时间
	private Date startTime;
	// Session最后交互时间
	private Date lastAccess;
	// Session timeout
	private long timeout;
	// 最后登录时间
    private Date lastLoginTime;
	// session 是否踢出
	private boolean sessionStatus = Boolean.TRUE;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public SysUserOnline(){
	}

	public SysUserOnline(SysUser user){
		super(user);
	}

	public String getLastLoginTime() {
		return sdf.format(lastLoginTime);
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getStartTime() {
		return sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getLastAccess() {
		return sdf.format(lastAccess);
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public boolean isSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(boolean sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	
	
}
