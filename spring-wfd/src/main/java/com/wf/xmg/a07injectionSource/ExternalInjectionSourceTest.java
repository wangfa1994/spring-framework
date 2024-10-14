package com.wf.xmg.a07injectionSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

//外部化配置作为依赖来源示例
@Configuration
@PropertySource(value = "META-INF/a07/externalInjectionSource.properties",encoding="utf-8")
public class ExternalInjectionSourceTest {

	@Value("${user.id:-1}")
	private Long id;

	@Value("${usr.name}")
	private String name;

	@Value("${user.resource:classpath://externalInjectionSource.properties}")
	private Resource resource;

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类） -> Spring Bean
		applicationContext.register(ExternalInjectionSourceTest.class);

		// 启动 Spring 应用上下文
		applicationContext.refresh();

		// 依赖查找 ExternalConfigurationDependencySourceDemo Bean
		ExternalInjectionSourceTest demo = applicationContext.getBean(ExternalInjectionSourceTest.class);

		System.out.println("demo.id = " + demo.id);
		System.out.println("demo.name = " + demo.name);
		System.out.println("demo.resource = " + demo.resource);
		Properties properties  = new Properties();
		try {
			properties.load(demo.resource.getInputStream());
			System.out.println(properties.getProperty("user.id"));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();
	}

	/**
	 *
	 * spring容器中的bean的来源有系统帮我们添加的系统bean，我们自己业务bean
	 *
	 * 系统添加的bean有： environment  systemProperties  systemEnvironment  applicationStartup 等
	 *
	 * 上下文在处理依赖关系的时候会从两个位置进行确定依赖来源：
	 * 1. spring容器中，包括我们业务bean 和 系统内建bean
	 * 2.非bean，游离在容器之外的bean，主要存放在 DefaultListableBeanFactory.resolvableDependencies 中的bean
	 *
	 *
	 *
	 *
	 */

}
