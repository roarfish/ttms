package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.Team;
/**
 * 团目业务表现层接口
 * @author zhoup
 *
 */
public interface TeamService {
	/**
	 * 查询全部团目信息
	 * @return
	 */
	List<Team> queryAllTeam();
	/**
	 * 分页查询
	 * @param startIndex
	 * @param pageCurrent
	 * @return
	 */
	Map<String, Object> queryAllTeamsByPage(String projectId,String valid,int pageCurrent);
	
	/**
	 * 设置团启用状态
	 * @param valid
	 */
	void updateValid(int valid,String id);
	
	/**
	 * 新增团信息
	 * @param team
	 */
	void saveTeams(Team team);
	
	/**
	 * 修改团信息
	 * @param team
	 */
	void updateTeams(Team team);
	
	/**
	 * 根据id查找团信息
	 * @param id
	 * @return
	 */
	Team queryTeamById(int id);
	
	/**
	 * 查询所有项目名
	 * @return
	 */
	List<Object> queryAllProjectsName();
}
