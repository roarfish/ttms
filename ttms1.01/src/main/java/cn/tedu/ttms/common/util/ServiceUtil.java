package cn.tedu.ttms.common.util;

import java.util.Map;

public interface ServiceUtil {
	
	Map<String, Object> queryPages(String obj, String valid, int pageCurrent,String dao);
	
}
