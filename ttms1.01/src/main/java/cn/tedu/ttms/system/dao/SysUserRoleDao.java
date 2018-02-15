package cn.tedu.ttms.system.dao;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDao {
	int isUsedByUser(@Param("roleId")Integer roleId);
	
	int insertObject(@Param("userId")Integer userId,@Param("roleIds")String[] roleIds);
	
	int deleteUserRoles(Integer userId);
	
	Set<String> findRoleIdsByUserId(Integer userId);
	
	List<Integer> findRoleIdsByUserIds(Integer userId);
}