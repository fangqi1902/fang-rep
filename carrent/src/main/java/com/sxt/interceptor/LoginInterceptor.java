package com.sxt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JInternalFrame.JDesktopIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sxt.sys.domain.User;
import com.sxt.sys.utils.CookieUtil;
import com.sxt.sys.utils.ThreadLocalUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 第一种方式是要定义的Interceptor类要实现了Spring的HandlerInterceptor 接口
 * 
 * 第二种方式是继承实现了HandlerInterceptor接口的类，比如Spring已经提供的实现了HandlerInterceptor接口的抽象类HandlerInterceptorAdapter
 * 
 * @author hrx
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private JedisPool pool;

	/**
	 * 进入Controller拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = CookieUtil.getCookieValue(request, "car-login-code");
		if(id==null) {
			//重定向到登录
			response.sendRedirect("login/toLogin.action");
		}
		Jedis jedis =null;
		String userJson = null;
		try {
			jedis = pool.getResource();
			userJson = jedis.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null!=jedis) {
				jedis.close();
			}
		}
		if(null!=userJson) {
			//得到java 的用户对象
			User user = JSON.parseObject(userJson,User.class);
			ThreadLocalUtil.setValue("user", user);
			//设置过期时间 距离上次操作超过30分钟
			jedis.expire(id, 1800);
			return true;
		}else {
			//重定向到登录界面
			response.sendRedirect("login/toLogin.action");
		}
		return false;
	}
	/**
	 * 在渲染页面之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 访问方法完毕后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
