package cn.tedu.ttms.system.controller;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.system.entity.SysRole;
import cn.tedu.ttms.system.service.SysRoleService;

@Controller
@RequestMapping("/role/")
public class SysRoleController {
	
	@Resource
	private SysRoleService sysroleservice;
	
	@RequestMapping("listUI")
	public String roleUI(){
		return "system/role_list";
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "system/role_edit";
	}
	/**
	 * 分页查询
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("queryRolesByPages")
	@ResponseBody
	public JsonResult doShowRolesPage(String name,int pageCurrent){
		Map<String, Object> roleList=sysroleservice.findObjects(name,pageCurrent);
		return new JsonResult(roleList);
	}
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteRole")
	@ResponseBody
	public JsonResult doDeleteRole(int id){
		sysroleservice.deleteRoleById(id);
		return new JsonResult();
	}
	/**
	 * 查询树节点信息
	 * @return
	 */
	@RequestMapping("doFindZtreeNodes")
	@ResponseBody
	public JsonResult doFindZtreeNodes(){
		return  new JsonResult(sysroleservice.findZtreeNodes());
	}
	/**
	 * 新增角色
	 * @return
	 */
	@RequestMapping("doAddRole")
	@ResponseBody
	public JsonResult doAddRole(SysRole role,String menuIdList){
		sysroleservice.saveObject(role, menuIdList);
		return new JsonResult();
	}
	/**
	 * 修改角色
	 * @param role
	 * @param menuIdList
	 * @return 
	 */
	@RequestMapping("doUpdateRole")
	@ResponseBody
	public JsonResult doUpdateRole(SysRole role,String menuIdList){
		sysroleservice.updateRole(role, menuIdList);
		return new JsonResult();
	}
	/**
	 * 根据roleId查询角色
	 * @param id
	 * @return
	 */
	@RequestMapping("doQueryRoleById")
	@ResponseBody
	public JsonResult doQueryRoleById(Integer id){
		return new JsonResult(sysroleservice.findMapById(id));
	}
}
