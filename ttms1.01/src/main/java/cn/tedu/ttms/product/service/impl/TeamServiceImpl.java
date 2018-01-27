package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
public class TeamServiceImpl implements TeamService {
	/**
	 * 注入
	 */
	@Resource
	private TeamDao dao;
	
	@Resource
	private ProjectDao prodao;
	/**
	 * 全部团目信息
	 */
	@Override
	public List<Team> queryAllTeam() {
		List<Team> list=dao.queryAllTeam();
		return list;
	}
	/**
	 * 分页查询
	 */
	@Override
	public Map<String, Object> queryAllTeamsByPage(String projectId,String valid,int pageCurrent) {
		PageObject pageobject=new PageObject();
		int startIndex=pageCurrent*pageobject.getPageSize();
		int rowCount=dao.queryTeamCount(projectId,valid);
		pageobject.setPageCurrent(pageCurrent);
		pageobject.setRowCount(rowCount);
		pageobject.setStartIndex(startIndex);
		List<Team> list=dao.queryTeamsByPage(projectId,valid,startIndex, pageobject.getPageSize());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", pageobject);
		map.put("teamList", list);
		return map;
	}
	/**
	 * 设置团启用状态
	 */
	@Override
	public void updateValid(int valid,String id) {
		dao.updateValid(valid ,id);
	}
	/**
	 * 新增团信息
	 */
	@Override
	public void saveTeams(Team team) {
		dao.doSaveTeams(team);
	}
	/**
	 * 修改团信息
	 */
	@Override
	public void updateTeams(Team team) {
		dao.doUpdateTeams(team);
	}
	/**
	 * 根据id查找团信息
	 */
	@Override
	public Team queryTeamById(int id) {
		return dao.queryTeamById(id);
	}
	/**
	 * 查询所有项目名
	 */
	@Override
	public List<Object> queryAllProjectsName() {
		return prodao.queryAllProject();
	}

}
