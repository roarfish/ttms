package cn.tedu.ttms.common.util.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.util.ServiceUtil;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.system.dao.SysRoleDao;
import cn.tedu.ttms.system.dao.SysUserDao;

@Service("serviceUtil")
public class ServiceUtilImpl implements ServiceUtil{
	@Resource
	protected ProjectDao projectDao;
	@Resource
	protected TeamDao teamDao;
	@Resource
	protected SysRoleDao roleDao;
	@Resource
	private SysUserDao userDao;
	
	public Map<String, Object> queryPages(String obj, String valid, int pageCurrent,String dao) {
		
		PageObject page=new PageObject();
		int startIndex=pageCurrent*page.getPageSize();
		Map<String,Object> lists=getPageObject(obj, valid, startIndex, page.getPageSize(), dao);
		page.setStartIndex(startIndex);
		page.setRowCount((Integer) lists.get("count"));
		page.setPageCurrent(pageCurrent);
		@SuppressWarnings("unchecked")
		List<Object> list=(List<Object>) lists.get("pages");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("Page", page);
		map.put("List", list);
		
		return map;
	}
	
	public Map<String,Object> getPageObject(String obj, String valid,int startIndex,int pageSize,String dao){
		
		Map<String,Object> list=new HashMap<String,Object>();
		if(dao.equals("project")){
			list.put("count", projectDao.queryRowCount(obj,valid));
			list.put("pages", projectDao.queryProjectByPage(obj, valid, startIndex, pageSize));
			return list;
		}else if(dao.equals("team")){
			list.put("count",teamDao.queryTeamCount(obj, valid));
			list.put("pages",teamDao.queryTeamsByPage(obj, valid, startIndex, pageSize));
			return list;
		}else if(dao.equals("roles")){
			list.put("count", roleDao.getRowCounts(obj));
			list.put("pages", roleDao.findPageObjects(obj, startIndex, pageSize));
			return list;
		}else if(dao.equals("users")){
			list.put("count", userDao.getRowCount(obj));
			list.put("pages", userDao.findPageObjects(obj, startIndex, pageSize));
			return list;
		}
		
		return null;
	}
}
