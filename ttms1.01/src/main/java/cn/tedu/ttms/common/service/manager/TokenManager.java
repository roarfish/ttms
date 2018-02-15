package cn.tedu.ttms.common.service.manager;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import cn.tedu.ttms.system.entity.SysUser;

/**
 * Shiro管理下的Token工具类
 * @author zhoup
 *
 */
public class TokenManager extends UsernamePasswordToken implements Serializable{

	private static final long serialVersionUID = -7467357594479933456L;

	/**
	 * 登录
	 * @param user
	 */
	public static void login(SysUser user){
		ShiroToken token=new ShiroToken(user.getUsername(), user.getPassword());
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated())return;
		subject.login(token);
	}
	
	/**
	 * 获取当前登录对象
	 * @return
	 */
	public static SysUser getToken(){
		SysUser user=(SysUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	
	/**
	 * 获取用户id
	 * @return
	 */
	public static Integer getUserId(){
		return getToken()==null ? null : getToken().getId();
	}
}
