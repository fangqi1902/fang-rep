package com.sxt.sys.utils;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {
	
	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>(){
		// 在new 时，该方法将会执行,初始化我们的值
		protected java.util.Map<String,Object> initialValue() {
		return new HashMap<String,Object>();
		}; 
	};
	
	public static Map<String, Object> getResource(){
		return threadLocal.get();
	}
	
	
	public static void setValue(String key,Object value) {
		if(key==null||value==null) {
			throw new RuntimeException("要存在线程里面的值不能为null");
		}
		
		Map<String, Object> map = getResource();
		map.put(key, value);
	}
	
	public static Object getValue(String key) {
		if(key==null) {
			throw new RuntimeException("获取线程里面的值时，key不能为null");
    	}
    	return getResource().get(key);
	}
	

}
