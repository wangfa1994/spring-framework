package com.wf.xmgAop02.a11;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 40 替换 targetSource
 *
 * @see org.springframework.aop.TargetSource
 *
 * 实现类
 * SingletonTargetSource
 * EmptyTargetSource
 * HotSwappableTargetSource
 *
 *
 */
public class TargetSourceDemo {

	public static void main(String[] args) {
		//TargetSource targetSource  = new SingletonTargetSource();
		// ProxyFactoryBean 中存在target source 用于追踪目标源

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a11/targetSource.xml");

		EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

		System.out.println(echoService.echo("Hello,World"));

		context.close();


		// adviceSupport

	}


}
