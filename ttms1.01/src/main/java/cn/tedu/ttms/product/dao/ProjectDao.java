package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Project;
/**
 * ��˳־ò�
 * @author zhoup
 *
 */
public interface ProjectDao {
	/**
	 * ��ѯȫ����Ŀ��Ϣ
	 * @return
	 */
	List<Object> queryAllProject();
	/**
	 * ��ҳ��ѯ
	 * @param name ��Ŀ��
	 * @param valid ����״̬
	 * @param startIndex ��ʼ����
	 * @param pageSize ҳ��������
	 * @return
	 */
	List<Project> queryProjectByPage(
			@Param("name")String name,
			@Param("valid")String valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
	/**
	 * ��ѯ�ܼ�¼��
	 * @param name ��Ŀ��
	 * @param valid ����״̬
	 * @return
	 */
	int queryRowCount(
			@Param("name")String name,
			@Param("valid")String valid );
	/**
	 * ������Ŀ
	 * @param project ��װ��������Ŀ��Ϣ
	 */
	void addProject(Project project);
	/**
	 * ����id��ѯ��Ŀ��Ϣ
	 * @return
	 */
	Project queryProjectById(int id);
	/**
	 * ������Ŀ
	 * @param project ��װ���޸���Ŀ��Ϣ
	 */
	void updateProject(Project project);
	/**
	 * �޸���Ŀ����״̬
	 * @param valid
	 * @param id
	 */
	void updateProject_valid(
			@Param("valid") int valid,
			@Param("id") String id );
}
