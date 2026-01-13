package com.wf.base;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class SpringMvcDebugApplication {

	public static void main(String[] args) throws LifecycleException {
		System.out.println("=== Spring MVC 源码调试启动 ===");

		// 1. 启动内嵌Tomcat
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.getConnector();

		// 2. 设置Web应用目录（关键！）
		String webappDir = new File("spring-wfd-web/src/main/webapp").getAbsolutePath();

		// 3. 添加Web应用上下文
		Context tomcatContext = tomcat.addWebapp("", webappDir);

		// 4. 移除默认的JSP Servlet映射，让Spring处理JSP
		removeDefaultJspServlet(tomcatContext);

		// 5. 创建Spring上下文
		AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
		springContext.setServletContext(tomcatContext.getServletContext()); // 关键！
		springContext.register(WebConfig.class);
		springContext.refresh(); // 必须调用refresh()

		// 6. 创建DispatcherServlet
		DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);

		// 7. 将DispatcherServlet添加到Tomcat
		Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet);
		tomcatContext.addServletMappingDecoded("/*", "dispatcher"); // 映射所有请求

		// 8. 设置欢迎页面
		tomcatContext.addWelcomeFile("index.jsp");

		// 9. 启动Tomcat
		tomcat.start();
		System.out.println("Tomcat已启动: http://localhost:8080");
		System.out.println("JSP页面: http://localhost:8080/index.jsp");
		System.out.println("欢迎页面: http://localhost:8080/");

		// 10. 等待服务器运行
		tomcat.getServer().await();
	}

	private static void removeDefaultJspServlet(Context context) {
		// 查找并移除默认的JSP Servlet映射
		String[] mappings = context.findServletMappings();
		for (String mapping : mappings) {
			String servletName = context.findServletMapping(mapping);
			if ("jsp".equals(servletName)) {
				context.removeServletMapping(mapping);
				System.out.println("已移除JSP映射: " + mapping);
			}
		}
	}
}
