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
	 * 查询全部节点信息
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String,Object>> list=dao.findObjects();
		return list;
	}

	/**
	 * 删除节点信息
	 * @param id
	 * @return
	 */
	@Override
	public void deleteObject(int id) {
		dao.deleteObject(id);
	}
	
	/**
	 * 
	 * 查询分类节点信息,在客户端以ztree形式进行展示
	 */
	@Override
	public List<Map<String, Object>> findTreeObjects() {
		return dao.findTreeObjects();
	}

	/**
	 * 新增产品信息
	 */
	@Override
	public void saveType(ProductType product) {
		dao.saveType(product);
	}

	/**
	 * 修改产品信息
	 * @param product
	 */
	@Override
	public void updateType(ProductType product) {
		dao.updateType(product);
	}

	/**
	 * 根据ID查询产品信息
	 * @param id
	 * @return
	 */
	@Override
	public List<Map<String,Object>> findTypeById(int id) {
		return dao.findTypeById(id);
	}

}
