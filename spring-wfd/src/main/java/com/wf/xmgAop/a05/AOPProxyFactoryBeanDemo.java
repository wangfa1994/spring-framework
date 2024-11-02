package com.wf.xmgAop.a05;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * ProxyFactoryBean 通过bean创建代理对象 ，更适合在容器中使用
 *
 *  xml配置驱动 创建spring aop 代理 对象
 *
 * 实现了FactoryBean 可以通过
 *
 *
 *
 * ProxyFactory 更底层的工具
 *
 */

public class AOPProxyFactoryBeanDemo {

	public static void main(String[] args) {

		proxyFactory();
		System.out.println("==========");
		//proxyFactoryBean();


	}

	/**
	 * 脱离了spring的容器，底层真正的实现对象 ProxyFactory
	 * ProxyFactory
	 */
	private static void proxyFactory() {
		DefaultEchoService defaultEchoService = new DefaultEchoService();

		// 注入目标对象
		ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);

		//proxyFactory.setTarget(defaultEchoService); // 利用这种方式比构造器少了些东西
		// 添加 advice 实现 MethodInterceptor < interceptor  < advice
		proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
		EchoService proxy = (EchoService)proxyFactory.getProxy();

		System.out.println(proxy.echo("Hello,World"));
		// 代理对象是基于什么产生的呢？ 而且在容器中代理的对象的 编号和这里代理的编号不一致
	}

	private static void proxyFactoryBean() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/aop/a05/aspect.xml");

		EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

		System.out.println(echoService.echo("Hello,World"));

		context.close();
	}

}
