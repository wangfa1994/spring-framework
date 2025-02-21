package com.wf.xmgAop02.a10;


import org.springframework.context.support.ClassPathXmlApplicationContext;

// 自动代理
public class BeanNameAutoProxyCreatorDemo {

	public static void main(String[] args) {
		// BeanNameAutoProxyCreator  最终属于 SmartInstantiationAwareBeanPostProcessor 属于  beanPostProcessor

		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a10/autoProxyBeanName.xml");

		applicationContext.refresh();

		EchoService echoService = applicationContext.getBean(EchoService.class);

		System.out.println(echoService.echo("Hello,World"));




	}
}
