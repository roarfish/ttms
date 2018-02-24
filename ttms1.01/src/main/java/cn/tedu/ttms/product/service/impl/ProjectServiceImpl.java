package cn.tedu.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.util.ServiceUtil;
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
	private ProjectDao projectDao;
	
	@Resource
	private ServiceUtil util;
	
	/**
	 * 查询全部项目信息
	 */
	
	public List<Object> queryAllProject() {
		List<Object> list=projectDao.queryAllProject();
;		return list;
	}
	/**
	 * 分页查询
	 */
	
	public Map<String, Object> queryProjectByPage(String name,String valid,int pageCurrent) {
		String dao="project";
		Map<String,Object> map=util.queryPages(name, valid, pageCurrent,dao);
		return map;
	}
	
	/**
	 * 添加项目
	 */
	
	public void addProject(Project project) {
		projectDao.addProject(project);
	}
	/**
	 * 根据ID查询项目信息
	 */
	
	public Project queryProjectById(int id) {
		Project project=projectDao.queryProjectById(id);
		return project;
	}
	/**
	 * 更新项目信息
	 */
	
	public void updateProject(Project project) {
		projectDao.updateProject(project);
	}
	/**
	 * 更新项目启用状态
	 */
	
	public void updateProject_valid(int valid, String id) {
		projectDao.updateProject_valid(valid, id);
	}


}
