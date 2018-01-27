package cn.tedu.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	
	@Resource
	private ProductTypeDao dao;


	/**
	 * ��ѯȫ���ڵ���Ϣ
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String,Object>> list=dao.findObjects();
		return list;
	}

	/**
	 * ɾ���ڵ���Ϣ
	 * @param id
	 * @return
	 */
	@Override
	public void deleteObject(int id) {
		dao.deleteObject(id);
	}
	
	/**
	 * 
	 * ��ѯ����ڵ���Ϣ,�ڿͻ�����ztree��ʽ����չʾ
	 */
	@Override
	public List<Map<String, Object>> findTreeObjects() {
		return dao.findTreeObjects();
	}

	/**
	 * ������Ʒ��Ϣ
	 */
	@Override
	public void saveType(ProductType product) {
		dao.saveType(product);
	}

	/**
	 * �޸Ĳ�Ʒ��Ϣ
	 * @param product
	 */
	@Override
	public void updateType(ProductType product) {
		dao.updateType(product);
	}

	/**
	 * ����ID��ѯ��Ʒ��Ϣ
	 * @param id
	 * @return
	 */
	@Override
	public List<Map<String,Object>> findTypeById(int id) {
		return dao.findTypeById(id);
	}

}
