package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Project;
/**
 * 后端持久层
 * @author zhoup
 *
 */
public interface ProjectDao {
	/**
	 * 查询全部项目信息
	 * @return
	 */
	List<Object> queryAllProject();
	/**
	 * 分页查询
	 * @param name 项目名
	 * @param valid 启用状态
	 * @param startIndex 开始个数
	 * @param pageSize 页面最大个数
	 * @return
	 */
	List<Object> queryProjectByPage(
			@Param("name")String name,
			@Param("valid")String valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
	/**
	 * 查询总记录数
	 * @param name 项目名
	 * @param valid 启用状态
	 * @return
	 */
	int queryRowCount(
			@Param("name")String name,
			@Param("valid")String valid );
	/**
	 * 新增项目
	 * @param project 封装的新增项目信息
	 */
	void addProject(Project project);
	/**
	 * 根据id查询项目信息
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
	void updateProject_valid(
			@Param("valid") int valid,
			@Param("id") String id );
}
