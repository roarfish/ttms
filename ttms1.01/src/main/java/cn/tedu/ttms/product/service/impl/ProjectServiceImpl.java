package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
/**
 * ����ʵ�ֲ�
 * @author zhoup
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	/**
	 * ע��־ò�
	 */
	@Resource
	private ProjectDao dao;
	/**
	 * ��ѯȫ����Ŀ��Ϣ
	 */
	@Override
	public List<Object> queryAllProject() {
		List<Object> list=dao.queryAllProject();
;		return list;
	}
	/**
	 * ��ҳ��ѯ
	 */
	@Override
	public Map<String, Object> queryProjectByPage(String name,String valid,int pageCurrent) {
		PageObject page=new PageObject();
		int startIndex=pageCurrent*page.getPageSize();
		int rowCount=dao.queryRowCount(name,valid);
		page.setStartIndex(startIndex);
		page.setRowCount(rowCount);
		page.setPageCurrent(pageCurrent);
		List<Project> list=dao.queryProjectByPage(name,valid,startIndex,page.getPageSize());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("page", page);
		map.put("projectList", list);
		return map;
	}
	/**
	 * ������Ŀ
	 */
	@Override
	public void addProject(Project project) {
		dao.addProject(project);
	}
	/**
	 * ����ID��ѯ��Ŀ��Ϣ
	 */
	@Override
	public Project queryProjectById(int id) {
		Project project=dao.queryProjectById(id);
		return project;
	}
	/**
	 * ������Ŀ��Ϣ
	 */
	@Override
	public void updateProject(Project project) {
		dao.updateProject(project);
	}
	/**
	 * ������Ŀ����״̬
	 */
	@Override
	public void updateProject_valid(int valid, String id) {
		dao.updateProject_valid(valid, id);
	}


}