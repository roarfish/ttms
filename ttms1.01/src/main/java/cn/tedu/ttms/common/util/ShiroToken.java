package cn.tedu.ttms.common.util;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;


import cn.tedu.ttms.system.entity.SysUser;

/**
 * 进一步封装token
 * @author zhoup
 *
 */
public class ShiroToken extends UsernamePasswordToken implements Serializable{

	private static final long serialVersionUID = 3786270830745970773L;

	public ShiroToken(String username,String pwd){
		super(username, pwd);//相当于( UsernamePasswordToken token = new UsernamePasswordToken(username, password);)
		this.pwd=pwd;
	}
	
	/**
	 * 获取当前登录的用户User对象
	 * @return
	 */
	public static Object getToken(){
		Object token = SecurityUtils.getSubject().getPrincipal();
		return token ;
	}
	
	/**
	 * 获取当前用户ID
	 * @return
	 */
	public static Integer getUserId(){
		return /*getToken()==null?null:getToken().getId()*/  5;
	}
	
	private String pwd;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
