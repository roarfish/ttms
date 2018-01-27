package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.ProductType;

public interface ProductTypeService {
	
	/**
	 * ��ѯ������Ϣ
	 * @return
	 */
	List<Map<String,Object>> findObjects();
	
	/**
	 * ɾ���ڵ���Ϣ
	 * @param id
	 */
	void deleteObject(int id);
	
	/**
	 * ��ѯ����ڵ���Ϣ,�ڿͻ�����ztree��ʽ����չʾ
	 * @return
	 */
	List<Map<String,Object>> findTreeObjects();
	
	/**
	 * ������Ʒ��Ϣ
	 */
	void saveType(ProductType product);
	
	/**
	 * �޸Ĳ�Ʒ��Ϣ
	 * @param product
	 */
	void updateType(ProductType product);
	
	/**
	 * ����ID��ѯ��Ʒ��Ϣ
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> findTypeById(int id);
}