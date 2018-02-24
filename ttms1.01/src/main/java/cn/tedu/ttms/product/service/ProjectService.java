package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.Project;

/**
 * 表现层
 * @author zhoup
 *
 */
public interface ProjectService {
	/**
	 * 查询全部项目信息
	 * @return
	 */
	List<Object> queryAllProject();
	/**
	 * 分页查询
	 * @param name 模糊查询的名称
	 * @param valid 启用状态
	 * @param pageCurrent 目标页数
	 * @return
	 */
	Map<String, Object> queryProjectByPage(String name,String valid,int pageCurrent);
	/**
	 * 新增项目
	 * @param project 封装的项目信息
	 */
	void addProject(Project project);
	/**
	 * 根据id查询项目信息
	 * @param id
	 * @return
	 */
	Project queryProjectById(int id);
	/**
	 * 更新项目
	 * @param project 封装的修改项目信息
	 */
	void updateProject(Project project);
	/**
	 * 修改项目启用状态
	 * @param valid
	 * @param id
	 */
	void updateProject_valid(int valid, String id);
	
}
