package cn.tedu.ttms.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.util.ServiceUtil;
import cn.tedu.ttms.system.dao.SysMenuDao;
import cn.tedu.ttms.system.dao.SysRoleDao;
import cn.tedu.ttms.system.dao.SysRoleMenuDao;
import cn.tedu.ttms.system.dao.SysUserRoleDao;
import cn.tedu.ttms.system.entity.SysRole;
import cn.tedu.ttms.system.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Resource
	private ServiceUtil util;
	@Resource
	private SysRoleDao roleDao;
	@Resource
	private SysMenuDao menuDao;
	@Resource
	private SysRoleMenuDao rolemenuDao;
	@Resource
	private SysUserRoleDao userroleDao;
	/**
	 * 分页查询
	 */
	
	public Map<String, Object> findObjects(String name, int pageCurrent) {
		String dao="roles";
		Map<String,Object> map=util.queryPages(name, "", pageCurrent, dao);
		return map;
	}
	/**
	 * 添加角色并赋予其相关权限
	 * @param role
	 * @param menuIdList
	 */
	
	public void saveObject(SysRole role, String menuIdList) {
		if(role.equals(null)){
			throw new NullPointerException("请输入完整的角色信息");
		}
		if(roleDao.insertObject(role)<0){
			throw new RuntimeException("添加角色失败！");
		}
		if(!(menuIdList==null)){
			String menuIds[]=menuIdList.split(",");
			if(rolemenuDao.insertRoleMenus(role.getId(), menuIds)<0){
				throw new RuntimeException("添加角色授权失败！");
			}
		}
	}
	/**
	 * 根据roleId查询角色
	 * @param roleId
	 * @return
	 */
	
	public Map<String, Object> findMapById(Integer roleId) {
		Map<String, Object> map=new HashMap<String,Object>();
		SysRole role=roleDao.findObjectById(roleId);
		List<Integer> menuIds=rolemenuDao.findMenuIdsByRoleId(roleId);
		map.put("role", role);
		map.put("permissions", menuIds);
		return map;
	}
	/**
	 * 修改角色并修改其权限
	 * @param role
	 * @param menuIdList
	 */
	
	public void updateRole(SysRole role, String menuIdList) {
		if(role.equals(null)){
			throw new NullPointerException("请输入完整的角色信息");
		}
		if(roleDao.updateObject(role)<0){
			throw new RuntimeException("修改角色失败！");
		}
		if(!(menuIdList==null)){
			String menuIds[]=menuIdList.split(",");
			rolemenuDao.deleteObject(role.getId());
			if(rolemenuDao.insertRoleMenus(role.getId(), menuIds)<0){
				throw new RuntimeException("添加角色授权失败！");
			}
		}
	}
	/**
	 * 查询树节点信息
	 */
	
	public List<Map<String, Object>> findZtreeNodes() {
		return menuDao.findZtreeNodes();
	}
	/**
	 * 根据id删除角色
	 */
	
	public void deleteRoleById(int id) {
		roleDao.deleteObject(id);
		rolemenuDao.deleteObject(id);
	}

}
