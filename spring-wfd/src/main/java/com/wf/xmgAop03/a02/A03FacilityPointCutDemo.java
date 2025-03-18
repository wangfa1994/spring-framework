package com.wf.xmgAop03.a02;

import com.wf.xmgAop03.a02.methodinterceptor.EchoServiceMethodInterceptor;
import com.wf.xmgAop03.a02.pointcut.EchoServiceExtendPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 *
 * 46 pointcut 便利的实现
 *
 *  - 静态 pointCut  StaticMethodMatcherPointcut
 *
 *  - 正则表达式 Pointcut  JdkRegexpMethodPointCut
 *
 * @see org.springframework.aop.support.StaticMethodMatcherPointcut
 *
 *
 *  - 控制流 pointcut  ControlFlowPointcut
 *
 *  这些便利的实现其实是在spring 引入AspectJ 之前自己的实现， spring帮开发人员进行的一些便捷处理，来进行我们pointcut的操作
 *
 */
public class A03FacilityPointCutDemo {


	public static void main(String[] args) {


		EchoServiceExtendPointcut echoServicePointcut = new EchoServiceExtendPointcut("echo",EchoService.class);

		//ComposablePointcut pointcut = new ComposablePointcut(EchoServicePointcut.INSTANCE);


		ComposablePointcut pointcut = new ComposablePointcut();
		// 组合实现
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

	}




}
