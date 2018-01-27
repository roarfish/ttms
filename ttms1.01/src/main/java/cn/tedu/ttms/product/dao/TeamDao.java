package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Team;

/**
 * 团目控制层
 * @author zhoup
 *
 */
public interface TeamDao {
	
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
	List<Team> queryTeamsByPage(
			@Param("projectId") String projectId,
			@Param("valid") String valid,
			@Param("startIndex") int startIndex,
			@Param("pageSize") int pageSize
			);
	
	/**
	 * 查询团目信息数量
	 * @return
	 */
	int queryTeamCount(
			@Param("projectId") String projectId,
			@Param("valid") String valid
			);
	
	/**
	 * 设置团启用状态
	 * @param valid
	 */
	void updateValid(
			@Param("valid")int valid,
			@Param("id") String id);
	
	/**
	 * 选中团信息
	 * @param team
	 */
	void doSaveTeams(Team team);
	
	/**
	 * 修改团信息
	 * @param team
	 */
	void doUpdateTeams(Team team);
	
	/**
	 * 根据id查找团信息
	 * @param id
	 * @return
	 */
	Team queryTeamById(int id);
}
