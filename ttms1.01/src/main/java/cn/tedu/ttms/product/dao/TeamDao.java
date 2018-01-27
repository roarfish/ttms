package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Team;

/**
 * ��Ŀ���Ʋ�
 * @author zhoup
 *
 */
public interface TeamDao {
	
	/**
	 * ��ѯȫ����Ŀ��Ϣ
	 * @return
	 */
	List<Team> queryAllTeam();
	
	/**
	 * ��ҳ��ѯ
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
	 * ��ѯ��Ŀ��Ϣ����
	 * @return
	 */
	int queryTeamCount(
			@Param("projectId") String projectId,
			@Param("valid") String valid
			);
	
	/**
	 * ����������״̬
	 * @param valid
	 */
	void updateValid(
			@Param("valid")int valid,
			@Param("id") String id);
	
	/**
	 * ѡ������Ϣ
	 * @param team
	 */
	void doSaveTeams(Team team);
	
	/**
	 * �޸�����Ϣ
	 * @param team
	 */
	void doUpdateTeams(Team team);
	
	/**
	 * ����id��������Ϣ
	 * @param id
	 * @return
	 */
	Team queryTeamById(int id);
}
