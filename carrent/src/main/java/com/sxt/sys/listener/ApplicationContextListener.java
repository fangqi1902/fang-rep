package com.sxt.sys.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
//  监听ServletContext创建的信息
@WebListener
public class ApplicationContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		String path = context.getContextPath();
		context.setAttribute("ctx",path);
		System.out.println("ApplicationContextListener--加载成功");	

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

}
