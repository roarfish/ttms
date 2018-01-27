package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.ProductType;

public interface ProductTypeService {
	
	/**
	 * 查询分类信息
	 * @return
	 */
	List<Map<String,Object>> findObjects();
	
	/**
	 * 删除节点信息
	 * @param id
	 */
	void deleteObject(int id);
	
	/**
	 * 查询分类节点信息,在客户端以ztree形式进行展示
	 * @return
	 */
	List<Map<String,Object>> findTreeObjects();
	
	/**
	 * 新增产品信息
	 */
	void saveType(ProductType product);
	
	/**
	 * 修改产品信息
	 * @param product
	 */
	void updateType(ProductType product);
	
	/**
	 * 根据ID查询产品信息
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> findTypeById(int id);
}
