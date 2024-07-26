package com.wf.xmg.a06injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

//01 xml下的setter注入
public class XmlSetterInjectionTest {

	public static void main(String[] args) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		String xmlResourcePath = "classpath:/META-INF/dependency-setter-injection.xml";
		// 加载 XML 资源，解析并且生成 BeanDefinition
		beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
		// 依赖查找并且创建 Bean
		UserHolder userHolder1 = beanFactory.getBean("holder1", UserHolder.class);
		System.out.println("userHolder1:"+userHolder1);
		UserHolder userHolder2 = beanFactory.getBean("holder2", UserHolder.class);
		System.out.println("userHolder2: " + userHolder2);


	}
}
