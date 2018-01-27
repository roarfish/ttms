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
 * ��Ŀҵ��ʵ�ֲ�
 * @author zhoup
 *
 */
@Service
public class TeamServiceImpl implements TeamService {
	/**
	 * ע��
	 */
	@Resource
	private TeamDao dao;
	
	@Resource
	private ProjectDao prodao;
	/**
	 * ȫ����Ŀ��Ϣ
	 */
	@Override
	public List<Team> queryAllTeam() {
		List<Team> list=dao.queryAllTeam();
		return list;
	}
	/**
	 * ��ҳ��ѯ
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
	 * ����������״̬
	 */
	@Override
	public void updateValid(int valid,String id) {
		dao.updateValid(valid ,id);
	}
	/**
	 * ��������Ϣ
	 */
	@Override
	public void saveTeams(Team team) {
		dao.doSaveTeams(team);
	}
	/**
	 * �޸�����Ϣ
	 */
	@Override
	public void updateTeams(Team team) {
		dao.doUpdateTeams(team);
	}
	/**
	 * ����id��������Ϣ
	 */
	@Override
	public Team queryTeamById(int id) {
		return dao.queryTeamById(id);
	}
	/**
	 * ��ѯ������Ŀ��
	 */
	@Override
	public List<Object> queryAllProjectsName() {
		return prodao.queryAllProject();
	}

}
