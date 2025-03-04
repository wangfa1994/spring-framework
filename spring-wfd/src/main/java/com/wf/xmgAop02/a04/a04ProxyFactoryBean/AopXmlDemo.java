package com.wf.xmgAop02.a04.a04ProxyFactoryBean;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 04 xml配置驱动 - 创建AOP代理
 *
 *
 *
 * @see ProxyFactoryBean  这里是代理对象实例
 *
 */
public class AopXmlDemo {


	public static void main(String[] args) {

		ClassPathXmlApplicationContext context  = new
				ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a04/a04Aspect.xml");


		EchoService defaultEchoService = context.getBean("echoServiceProxyFactoryBean",EchoService.class);
		System.out.println(defaultEchoService.echo("hello"));


		System.out.println("=============添加了Method Interceptor");
		// 添加了方法拦截器之后，就会进行方法拦截，并且MethodInterceptor 是一个advice，
		EchoService defaultEchoService2 = context.getBean("echoServiceProxyFactoryBeanMethodIntercept",EchoService.class); //这里得到是java的动态代理，而不是cglib的动态代理
		System.out.println(defaultEchoService2.echo("hello"));


	}



}
