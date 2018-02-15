package cn.tedu.ttms.system.controller;
import org.apache.shiro.authc.DisabledAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.service.manager.TokenManager;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.system.entity.SysUser;

@Controller
public class SysLoginController {
	@RequestMapping("/loginUI")
	public String login(){
		return "login";
	}
	/**登录操作*/
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(SysUser user){
		try {
			System.out.println(user.getUsername()+"/"+user.getPassword());
			TokenManager.login(user); 
		} catch (DisabledAccountException e) {
			throw new ServiceException("账户已被禁用");
		} catch (Exception e) {
			throw new ServiceException("账户或密码错误");
		}
		return new JsonResult();
	}
}
