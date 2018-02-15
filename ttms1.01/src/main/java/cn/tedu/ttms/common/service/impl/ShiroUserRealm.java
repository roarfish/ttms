package cn.tedu.ttms.common.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import cn.tedu.ttms.common.service.SysShiroService;
import cn.tedu.ttms.common.service.manager.ShiroToken;
import cn.tedu.ttms.common.service.manager.TokenManager;
import cn.tedu.ttms.system.dao.SysUserDao;
import cn.tedu.ttms.system.dao.SysUserRoleDao;
import cn.tedu.ttms.system.entity.SysUser;

public class ShiroUserRealm extends AuthorizingRealm{
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysUserRoleDao userroledao;
	@Resource
	private SysShiroService shiroservice;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("==doGetAuthorizationInfo==");
		Integer userId=TokenManager.getUserId();
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		//根据用户ID查询角色（role），放入到Authorization里。
		Set<String> roles=userroledao.findRoleIdsByUserId(userId);
		info.setRoles(roles);
		//根据用户ID查询权限（permission），放入到Authorization里。
		Set<String> permissions=userroledao.findRoleIdsByUserId(userId);
		info.setStringPermissions(permissions);
		return info;
		
		
		/*SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		int userId = user.getId();		
		List<String> permsList = new ArrayList<>();
		permsList = sysUserDao.findUserPermissions(userId);
		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perm : permsList){
			if(perm!=null && !("".equals(perm))){
				permsSet.add(perm);
			}
		}
		System.out.println("permsSet="+permsSet);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;*/
	}

	/**
	 * 认证,登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authtoken) throws AuthenticationException {
        System.out.println("==doGetAuthenticationInfo==");
        ShiroToken token=(ShiroToken) authtoken;
        //查询用户
        SysUser user=shiroservice.login(token.getUsername());
        if(user==null){//判断用户身份存在
        	throw new AccountException("用户名错误");
        }else if(user.getValid()==0){//判断用户身份被禁用
        	throw new DisabledAccountException("此用户已被禁用");
        }
        //盐值
      	ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
      	//自动进行密码比对
		return new SimpleAuthenticationInfo(user, user.getPassword(),credentialsSalt,getName());
        
		
		
		/*//1. 把 AuthenticationToken 转换为 UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) authtoken;
		//2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();
		//判断用户名是否存在，若存在，返回user对象
		SysUser user =sysUserDao.findObjectByName(username);
		//盐值. 
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		//自动完成密码比对   - 密码的比对:
		//通过 AuthenticatingRealm 的 credentialsMatcher 属性来进行的密码的比对!
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(),credentialsSalt,getName());
		SecurityUtils.getSubject().getSession().setAttribute("currentUser",user);
		return info;*/
	}
}
