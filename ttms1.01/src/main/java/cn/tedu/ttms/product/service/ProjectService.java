package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.Project;

/**
 * ���ֲ�
 * @author zhoup
 *
 */
public interface ProjectService {
	/**
	 * ��ѯȫ����Ŀ��Ϣ
	 * @return
	 */
	List<Object> queryAllProject();
	/**
	 * ��ҳ��ѯ
	 * @param name ģ����ѯ������
	 * @param valid ����״̬
	 * @param pageCurrent Ŀ��ҳ��
	 * @return
	 */
	Map<String, Object> queryProjectByPage(String name,String valid,int pageCurrent);
	/**
	 * ������Ŀ
	 * @param project ��װ����Ŀ��Ϣ
	 */
	void addProject(Project project);
	/**
	 * ����id��ѯ��Ŀ��Ϣ
	 * @param id
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
	void updateProject_valid(int valid, String id);
}
