package cn.tedu.ttms.product.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;

@Controller
@RequestMapping("/team/")
public class TeamController {
	
	/**
	 * 注入实现的内容
	 */
	@Resource
	private TeamService teamservice;
	
	/**
	 * 加载团目信息展示页面
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(){
		return "product/team_list";
	}
	
	/**
	 * 加载团目信息编辑页面
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(){
		return "product/team_edit";
	}
	
	/**
	 * 查询全部团目信息
	 * @return
	 */
	@RequestMapping("doQueryAllTeams")
	@ResponseBody
	public JsonResult doQueryAllTeams(){
		return new JsonResult(teamservice.queryAllTeam());
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@RequestMapping("doQueryAllTeasByPage")
	@ResponseBody
	public JsonResult doQueryAllTeamsByPage(String projectId,String valid,int pageCurrent){
		return new JsonResult(teamservice.queryAllTeamsByPage(projectId,valid,pageCurrent));
		
	}
	
	/**
	 * 设置团启用状态
	 * @param valid
	 * @return
	 */
	@RequestMapping("doSetValid")
	@ResponseBody
	public JsonResult doSetValid(int valid,String ids){
		String[] id=ids.split(",");
		for(int i=1;i<id.length;i++){
			teamservice.updateValid(valid,id[i]);
		}
		return new JsonResult();
	}
	
	/**
	 * 新增团信息
	 * @param team
	 * @return
	 */
	@RequestMapping("doSaveTeams")
	@ResponseBody
	public JsonResult doSaveTeams(Team team){
		teamservice.saveTeams(team);
		return new JsonResult();
	}
	
	/**
	 * 修改团信息
	 * @param team
	 * @return
	 */
	@RequestMapping("doUpdateTeams")
	@ResponseBody
	public JsonResult doUpdateTeams(Team team){
		teamservice.updateTeams(team);
		return new JsonResult();
	}
	
	/**
	 * 根据id查找团信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doQueryTeamById")
	@ResponseBody
	public JsonResult doQueryTeamById(int id){
		return new JsonResult(teamservice.queryTeamById(id));
	}
	
	/**
	 * 查询所有项目名
	 * @return
	 */
	@RequestMapping("doQueryAllProjectName")
	@ResponseBody
	public JsonResult doQueryAllProjectName(){
		return new JsonResult(teamservice.queryAllProjectsName());
	}
}
