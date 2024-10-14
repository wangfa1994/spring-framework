package com.wf.xmg.a06injection;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class AutoWiringInjectTest {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(AutoWiringInjectTest.class);



		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
		xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/a06/dependency-autowiring-injection.xml");


		applicationContext.refresh();

		Map<String, UserHolder> beansOfType = applicationContext.getBeansOfType(UserHolder.class);

		beansOfType.forEach((k,v)->{
			System.out.println(k+"--->"+v);
		});

		// 显示地关闭 Spring 应用上下文
		applicationContext.close();


	}


	/*@Bean(value = "userHolderByTypeBean",autowire = Autowire.BY_TYPE)
	public UserHolder userHolderByTypeBean(User user){
		return new UserHolder(user);
	}*/
		// 为什么指定了 autowire = Autowire.BY_TYPE 就会失败呢？
	/*@Bean(value = "userHolderByNameBean",autowire = Autowire.BY_NAME)
	public UserHolder userHolderByNameBean(User user){
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user);
		return userHolder;
	}*/



}
