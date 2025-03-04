package com.wf.xmgAop02.a04.a05ProxyFactory;


import com.wf.xmgAop02.a04.a04ProxyFactoryBean.CustomerMethodInterceptor;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.DefaultEchoService;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 26
 * ProxyFactory 代理类的使用 标准代理工厂
 *
 * 脱离spring
 *
 * ProxyFactory 和  ProxyFactoryBean 都是产生代理对象的类，
 *
 * 只不过 ProxyFactory 更加独立，底层
 * ProxyFactoryBean 则更合spring容器进行契合。
 *
 */


public class ProxyFactoryDemo {

	public static void main(String[] args) {
		DefaultEchoService defaultEchoService = new DefaultEchoService();
		ProxyFactory proxyFactory  = new ProxyFactory(defaultEchoService);

		proxyFactory.addAdvice(new CustomerMethodInterceptor()); // 添加我们的拦截逻辑，

		EchoService proxy = (EchoService)proxyFactory.getProxy(); //这里得到是java的动态代理，而不是cglib的动态代理，如果传递的类没有实现接口呢？
		System.out.println(proxy.echo("hello"));


	}
}
