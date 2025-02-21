package com.wf.xmgAop02.a11;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TargetSourceDemo {

	public static void main(String[] args) {
		//TargetSource targetSource  = new SingletonTargetSource();
		// ProxyFactoryBean 中存在target source

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a11/targetSource.xml");

		EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

		System.out.println(echoService.echo("Hello,World"));

		context.close();


		// adviceSupport

	}


}
