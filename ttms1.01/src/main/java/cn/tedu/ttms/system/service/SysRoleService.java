package cn.tedu.ttms.system.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.system.entity.SysRole;


public interface SysRoleService {

	Map<String, Object> findObjects(String name,int pageCurrent);
	void saveObject(SysRole role,String menuIdList);
	Map<String,Object> findMapById(Integer roleId);
	void updateRole(SysRole role,String menuIdList);
	List<Map<String, Object>> findZtreeNodes();
	void deleteRoleById(int id);

}
