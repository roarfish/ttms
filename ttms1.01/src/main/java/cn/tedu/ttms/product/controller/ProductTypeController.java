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
	 * 查询全部节点信息
	 * @return
	 */
	@RequestMapping("doFindObjectsForTree")
	@ResponseBody
	public JsonResult doFindObjectsForTree(){
		List<Map<String, Object>> list=productTypeService.findObjects();
		return new JsonResult(list);
	}
	
	/**
	 * 删除节点信息
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
	 * 查询分类节点信息,在客户端以ztree形式进行展示
	 * @return
	 */
	@RequestMapping("doFindTreeObjects")
	@ResponseBody
	public JsonResult doFindTreeObjects(){
		return new JsonResult(productTypeService.findTreeObjects());
	}
	
	/**
	 * 新增产品类型
	 * @return
	 */
	@RequestMapping("doSaveType")
	@ResponseBody
	public JsonResult doSaveType(ProductType product){
		productTypeService.saveType(product);
		return new JsonResult();
	}
	
	/**
	 * 修改产品信息
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
	 * 根据ID查询产品信息
	 * @param id
	 * @return
	 */
	@RequestMapping("doFindTypeById")
	@ResponseBody
	public JsonResult doFindTypeById(int id){
		return new JsonResult(productTypeService.findTypeById(id));
	}
}
