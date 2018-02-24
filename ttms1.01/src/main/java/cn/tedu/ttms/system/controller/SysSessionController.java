package cn.tedu.ttms.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.session.CustomSessionManager;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.system.entity.SysUserOnline;

@RequestMapping("/session/")
@Controller
public class SysSessionController {
	
	@Autowired
	CustomSessionManager customSessionManager;
	
	/**
	 * 跳转在线用户页面
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(){
		return "system/sessionUser_list";
	}
	
	/**
	 * 跳转详情页面
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("dataDetail")
	public String detailUI(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		String sessionId = request.getParameter("sessionId");
		SysUserOnline online = customSessionManager.getSession(sessionId);
		request.setAttribute("onlineUser", online);
		return "system/sessionUser_detail";
	}
	
	/**
	 * 在线用户管理
	 * @return
	 */
	@RequestMapping("online")
	@ResponseBody
	public JsonResult online(){
		List<SysUserOnline> list = customSessionManager.getAllUser();
		return new JsonResult(list);
	}
}
