package com.wf.base;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class SpringMvcDebugApplicationSimple {

	public static void main(String[] args) throws LifecycleException {
		System.out.println("=== Spring MVC 源码调试启动 ===");

		// 1. 启动内嵌Tomcat
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.getConnector(); // 重要：初始化连接器

		// 2. 创建Spring上下文
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class);

		// 3. 创建DispatcherServlet
		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

		// 4. 将DispatcherServlet添加到Tomcat
		Context tomcatContext = tomcat.addContext("", null);
		Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet);
		tomcatContext.addServletMappingDecoded("/", "dispatcher");

		// 5. 设置初始化参数（可选）
		// dispatcherServlet.setContextConfigLocation("");

		// 6. 启动Tomcat
		tomcat.start();
		System.out.println("Tomcat已启动: http://localhost:8080");
		System.out.println("可以在DispatcherServlet.doDispatch()设置断点调试");

		// 7. 等待服务器运行
		tomcat.getServer().await();
	}
}
