package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.util.ServiceUtil;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;
/**
 * 团目业务实现层
 * @author zhoup
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class TeamServiceImpl implements TeamService {
	/**
	 * 注入
	 */
	@Resource
	private TeamDao dao;
	
	@Resource
	private ProjectDao prodao;
	
	@Resource
	private ServiceUtil util;
	/**
	 * 全部团目信息
	 */
	
	public List<Team> queryAllTeam() {
		List<Team> list=dao.queryAllTeam();
		return list;
	}
	/**
	 * 分页查询
	 */
	
	public Map<String, Object> queryAllTeamsByPage(String projectId,String valid,int pageCurrent) {
		
		String dao="team";
		Map<String,Object> map=util.queryPages(projectId, valid, pageCurrent,dao);
		
		
		/*PageObject pageobject=new PageObject();
		int startIndex=pageCurrent*pageobject.getPageSize();
		int rowCount=dao.queryTeamCount(projectId,valid);
		pageobject.setPageCurrent(pageCurrent);
		pageobject.setRowCount(rowCount);
		pageobject.setStartIndex(startIndex);
		List<Team> list=dao.queryTeamsByPage(projectId,valid,startIndex, pageobject.getPageSize());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", pageobject);
		map.put("teamList", list);*/
		return map;
	}
	/**
	 * 设置团启用状态
	 */
	
	public void updateValid(int valid,String id) {
		dao.updateValid(valid ,id);
	}
	/**
	 * 新增团信息
	 */
	
	public void saveTeams(Team team) {
		dao.doSaveTeams(team);
	}
	/**
	 * 修改团信息
	 */
	
	public void updateTeams(Team team) {
		dao.doUpdateTeams(team);
	}
	/**
	 * 根据id查找团信息
	 */
	
	public Team queryTeamById(int id) {
		return dao.queryTeamById(id);
	}
	/**
	 * 查询所有项目名
	 */
	
	public List<Object> queryAllProjectsName() {
		return prodao.queryAllProject();
	}

}
