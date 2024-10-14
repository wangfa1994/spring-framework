package com.wf.xmg.a06injection;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 通过beanDefinition的api进行赋值处理
public class APIConstructorTest {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		// 在api中使用设置构造器的参数
		BeanDefinitionBuilder beanDefinitionBuilder = getBeanDefinitionBuilder();
		applicationContext.registerBeanDefinition("userHolder", beanDefinitionBuilder.getBeanDefinition());


		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/a06/dependency-api-injection.xml");


		applicationContext.refresh();

		// 依赖查找并且创建 Bean
		UserHolder userHolder = applicationContext.getBean(UserHolder.class);
		System.out.println(userHolder);

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();

	}

	private static BeanDefinitionBuilder getBeanDefinitionBuilder() {


		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
		// 通过构造器参数进行注入
		beanDefinitionBuilder.addConstructorArgReference("user");
		return beanDefinitionBuilder;

	}


}
