package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;

@Controller
@RequestMapping("/type/")
public class ProductTypeController {
	
	@Resource
	private ProductTypeService productTypeService;
	
	@RequestMapping("listUI")
	public String listUI(){
		return "product/type_list";
	}
	
	@RequestMapping("editUI")
	public String editUI(){
		return "product/type_edit";
	}
	
	/**
	 * ��ѯȫ���ڵ���Ϣ
	 * @return
	 */
	@RequestMapping("doFindObjectsForTree")
	@ResponseBody
	public JsonResult doFindObjectsForTree(){
		List<Map<String, Object>> list=productTypeService.findObjects();
		return new JsonResult(list);
	}
	
	/**
	 * ɾ���ڵ���Ϣ
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(int id){
		productTypeService.deleteObject(id);
		return new JsonResult();
	}
	
	/**
	 * ��ѯ����ڵ���Ϣ,�ڿͻ�����ztree��ʽ����չʾ
	 * @return
	 */
	@RequestMapping("doFindTreeObjects")
	@ResponseBody
	public JsonResult doFindTreeObjects(){
		return new JsonResult(productTypeService.findTreeObjects());
	}
	
	/**
	 * ������Ʒ����
	 * @return
	 */
	@RequestMapping("doSaveType")
	@ResponseBody
	public JsonResult doSaveType(ProductType product){
		productTypeService.saveType(product);
		return new JsonResult();
	}
	
	/**
	 * �޸Ĳ�Ʒ��Ϣ
	 * @param product
	 * @return
	 */
	@RequestMapping("doUpdateType")
	@ResponseBody
	public JsonResult doUpdateType(ProductType product){
		productTypeService.updateType(product);
		return new JsonResult();
	}
	
	/**
	 * ����ID��ѯ��Ʒ��Ϣ
	 * @param id
	 * @return
	 */
	@RequestMapping("doFindTypeById")
	@ResponseBody
	public JsonResult doFindTypeById(int id){
		return new JsonResult(productTypeService.findTypeById(id));
	}
}
