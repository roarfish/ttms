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
	 * ע��ʵ�ֵ�����
	 */
	@Resource
	private TeamService teamservice;
	
	/**
	 * ������Ŀ��Ϣչʾҳ��
	 * @return
	 */
	@RequestMapping("listUI")
	public String listUI(){
		return "product/team_list";
	}
	
	/**
	 * ������Ŀ��Ϣ�༭ҳ��
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(){
		return "product/team_edit";
	}
	
	/**
	 * ��ѯȫ����Ŀ��Ϣ
	 * @return
	 */
	@RequestMapping("doQueryAllTeams")
	@ResponseBody
	public JsonResult doQueryAllTeams(){
		return new JsonResult(teamservice.queryAllTeam());
	}
	
	/**
	 * ��ҳ��ѯ
	 * @return
	 */
	@RequestMapping("doQueryAllTeasByPage")
	@ResponseBody
	public JsonResult doQueryAllTeamsByPage(String projectId,String valid,int pageCurrent){
		return new JsonResult(teamservice.queryAllTeamsByPage(projectId,valid,pageCurrent));
		
	}
	
	/**
	 * ����������״̬
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
	 * ��������Ϣ
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
	 * �޸�����Ϣ
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
	 * ����id��������Ϣ
	 * @param id
	 * @return
	 */
	@RequestMapping("doQueryTeamById")
	@ResponseBody
	public JsonResult doQueryTeamById(int id){
		return new JsonResult(teamservice.queryTeamById(id));
	}
	
	/**
	 * ��ѯ������Ŀ��
	 * @return
	 */
	@RequestMapping("doQueryAllProjectName")
	@ResponseBody
	public JsonResult doQueryAllProjectName(){
		return new JsonResult(teamservice.queryAllProjectsName());
	}
}
