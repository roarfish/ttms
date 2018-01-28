package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
/**
 * 表现实现层
 * @author zhoup
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ProjectServiceImpl implements ProjectService {
	/**
	 * 注入持久层
	 */
	@Resource
	private ProjectDao dao;
	/**
	 * 查询全部项目信息
	 */
	@Override
	public List<Object> queryAllProject() {
		List<Object> list=dao.queryAllProject();
;		return list;
	}
	/**
	 * 分页查询
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
	 * 添加项目
	 */
	@Override
	public void addProject(Project project) {
		dao.addProject(project);
	}
	/**
	 * 根据ID查询项目信息
	 */
	@Override
	public Project queryProjectById(int id) {
		Project project=dao.queryProjectById(id);
		return project;
	}
	/**
	 * 更新项目信息
	 */
	@Override
	public void updateProject(Project project) {
		dao.updateProject(project);
	}
	/**
	 * 更新项目启用状态
	 */
	@Override
	public void updateProject_valid(int valid, String id) {
		dao.updateProject_valid(valid, id);
	}


}
