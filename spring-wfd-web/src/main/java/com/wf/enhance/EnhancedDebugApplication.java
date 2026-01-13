package com.wf.enhance;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EnhancedDebugApplication {
	private static final Logger logger = LoggerFactory.getLogger(EnhancedDebugApplication.class);

	public static void main(String[] args) throws LifecycleException, InterruptedException {
		logger.info("=== Spring MVC 增强调试模式启动 ===");

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(9090);
		tomcat.getConnector();

		// 启用热加载
		Context context = tomcat.addContext("", null);
		context.setReloadable(true);

		// 创建Spring上下文
		AnnotationConfigWebApplicationContext springContext =
				new AnnotationConfigWebApplicationContext();
		springContext.register(WebConfig.class);
		springContext.refresh();

		// 创建DispatcherServlet
		DispatcherServlet servlet = new DispatcherServlet(springContext);

		// 设置调试参数
		servlet.setDispatchOptionsRequest(true);
		servlet.setDispatchTraceRequest(true);

		Tomcat.addServlet(context, "spring", servlet);
		context.addServletMappingDecoded("/*", "spring");

		tomcat.start();

		logger.info("服务器已启动: http://localhost:9090");
		logger.info("可用端点:");
		logger.info("  GET /hello     - 基础测试");
		logger.info("  GET /debug     - 调试入口");
		logger.info("  GET /params?name=xxx - 参数测试");

		// 保持运行
		Thread.currentThread().join();
	}
}
