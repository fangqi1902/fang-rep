package com.sxt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sxt.sys.utils.SerializUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component // 开启切面扫描
@Aspect // 专门配置切面，切入写什么代码
public class CacheAop {
	
	@Autowired
	private JedisPool pool;
	
	@Before(value = "execution(* com.sxt.sys.service.impl.MenuServiceImpl.queryAllMenus(..))")
	public void preMethod() {
		System.err.println("在查询之前运行");
	}
	
	@After(value = "execution(* com.sxt.sys.service.impl.MenuServiceImpl.queryAllMenus(..))")
	public void afterMethod() {
		System.err.println("在查询之后运行");
	}
	@Around(value = "execution(* com.sxt.sys.service.impl.MenuServiceImpl.queryAllMenus(..))")
	public Object  aroundMethod(ProceedingJoinPoint point) {
		Jedis jedis = pool.getResource();
		String key ="carrent-menu-admin";
		if(jedis.exists(key.getBytes())) {
			byte[] bs = jedis.get(key.getBytes());
			jedis.close();
			return SerializUtil.deSerialization(bs);
		}else {
			try {
				Object result = point.proceed(point.getArgs());
				jedis.set(key.getBytes(), SerializUtil.serialization(result));
				return result;
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return null;
	}
	@Around(value = "execution(* com.sxt.sys.service.impl.MenuServiceImpl.queryAllMenusByUid(..))")
	public Object aroundMethodUser(ProceedingJoinPoint point) {
		Jedis jedis = pool.getResource();
		String key ="carrent-menu-user";
		Object[] args = point.getArgs();
		for (Object object : args) {
			key+=object.hashCode();
		}
		if(jedis.exists(key.getBytes())) {
			byte[] bs = jedis.get(key.getBytes());
			jedis.close();
			return SerializUtil.deSerialization(bs);
		}else {
			Object result = null;
			try {
				result = point.proceed(point.getArgs());
			} catch (Throwable e) {
				e.printStackTrace();
			}
			jedis.set(key.getBytes(), SerializUtil.serialization(result));
			jedis.close();
			return result;
		}
		
	}
	
	
}
