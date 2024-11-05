package com.wf.xmgAop03.a02;


import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

// pointcut 组合实现 ComposablePointcut  组合模式
// 为pointCut的一个功能实现类
public class ComposablePointcutDemo {


	public static void main(String[] args) {

		EchoServiceExtendPointcut echoServicePointcut = new EchoServiceExtendPointcut("echo",EchoService.class);

		 //ComposablePointcut pointcut = new ComposablePointcut(EchoServicePointcut.INSTANCE);
		ComposablePointcut pointcut = new ComposablePointcut();


		pointcut.intersection(echoServicePointcut.getClassFilter());
		pointcut.intersection(echoServicePointcut.getMethodMatcher());

		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());

		DefaultEchoService defaultEchoService = new DefaultEchoService();

		ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);

		// Advisor 和advise 关系，实际上 advise本身是一个动作，在动作前需要关联一个pointcut ，
		// pointcut 和 advise的关联需要Advisor进行承载
		// advisor 是 pointcut 和 advise的关联
		proxyFactory.addAdvisor(advisor);

		EchoService proxy = (EchoService)proxyFactory.getProxy();

		System.out.println(proxy.echo("hello"));



		//  便利实现Pointcut， 是我们开发人员可以快速开发使用的
		// StaticMethodMatcherPointcut 类
		// JdkRegexpMethodPointcut  类
		// ControlFlowPointcut 类

	}
}
