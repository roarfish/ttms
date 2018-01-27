package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.Team;
/**
 * ��Ŀҵ����ֲ�ӿ�
 * @author zhoup
 *
 */
public interface TeamService {
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
	Map<String, Object> queryAllTeamsByPage(String projectId,String valid,int pageCurrent);
	
	/**
	 * ����������״̬
	 * @param valid
	 */
	void updateValid(int valid,String id);
	
	/**
	 * ��������Ϣ
	 * @param team
	 */
	void saveTeams(Team team);
	
	/**
	 * �޸�����Ϣ
	 * @param team
	 */
	void updateTeams(Team team);
	
	/**
	 * ����id��������Ϣ
	 * @param id
	 * @return
	 */
	Team queryTeamById(int id);
	
	/**
	 * ��ѯ������Ŀ��
	 * @return
	 */
	List<Object> queryAllProjectsName();
}
