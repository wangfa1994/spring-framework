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
@PropertySource(value = "META-INF/externalInjectionSource.properties",encoding="utf-8")
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

}
