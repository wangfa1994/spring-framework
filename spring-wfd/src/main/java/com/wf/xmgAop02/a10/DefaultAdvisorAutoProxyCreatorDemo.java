package com.wf.xmgAop02.a10;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DefaultAdvisorAutoProxyCreatorDemo {

	public static void main(String[] args) {

		// DefaultAdvisorAutoProxyCreator 能够自动识别我们的advisor 真正变成全自动
		// AbstractAdvisorAutoProxyCreator
		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a10/autoProxyDefaultAdvisor.xml");

		applicationContext.refresh();

		EchoService echoService = applicationContext.getBean(EchoService.class);

		System.out.println(echoService.echo("Hello,World"));
	}
}
