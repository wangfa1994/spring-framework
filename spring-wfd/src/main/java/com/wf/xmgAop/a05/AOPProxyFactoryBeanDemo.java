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
	 *  基于 ProxyFactory 类进行处理
	 * 脱离了spring的容器，底层真正的实现对象 ProxyFactory
	 * ProxyFactory 与 AspectJProxyFactory 是什么关系呢？
	 *  他们都继承了 ProxyCreatorSupport 类，说明他们都是对应的代理工厂的实现，只不过是应用于不同的业务场景吧
	 *  ProxyFactoryBean 也是继承了 ProxyCreatorSupport 类
	 *
	 *
	 * ProxyCreatorSupport 代理工厂的基类，这个里面内置了一个代理工厂 AopProxyFactory ，然后会通过代理工厂进行产生我们的代理对象
	 *
	 *
	 * AopProxyFactory 接口之定义了创建代理对象的方法，简单工厂模式，
	 *
	 *
	 * 又因为
	 * ProxyFactoryBean  ProxyFactory 继承了 ProxyCreatorSupport 所以说他们也有生产代理对象的功能，只不过这个功能被基类的 ProxyCreatorSupport实现了
	 *
	 *
	 * AopProxyFactory接口 与 ProxyFactory类的区别
	 *
	 *
	 * AopProxyFactory接口 是自己一个单独的体系，用来创建对象的，简单工厂模版 ，只有一个创建代理对象的方法， 他只有一个实现类 DefaultAopProxyFactory 用来产生我们的代理对象
	 *
	 * ProxyFactory类 AOP编程的一个实现类， 主要是进行我们的aop执行，内置了AopProxyFactory用来产生代理对象， 继承AdvisedSupport则主要支持通知。
	 *
	 *
	 *
	 *
	 *
	 */
	private static void proxyFactory() {
		// 目标对象
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

	/**
	 *
	 *
	 *
	 */

}
