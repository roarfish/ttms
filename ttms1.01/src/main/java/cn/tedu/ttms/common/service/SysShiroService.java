package cn.tedu.ttms.common.service;

import cn.tedu.ttms.system.entity.SysUser;

public interface SysShiroService {
	SysUser login(String username);
}
