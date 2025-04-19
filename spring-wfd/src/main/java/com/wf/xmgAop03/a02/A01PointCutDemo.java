package com.wf.xmgAop03.a02;

// PointCut  体系 类
//
import com.wf.xmgAop03.a02.methodinterceptor.EchoServiceMethodInterceptor;
import com.wf.xmgAop03.a02.pointcut.EchoServiceEchoMethodPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/** 44 . joinpoint条件接口
 *
 *   joinpoint 条件接口 pointcut
 * @see org.springframework.aop.Pointcut
 *
 *   核心组件
 *   	- 类过滤器 classFilter
 *   	- 方法匹配器 MethodMatcher
 *
 *
 *
 * Pointcut 是用来定义“在哪些 Joinpoint 上应用 Advice”的规则。
 * 它通常需要更强的表达能力（例如通过正则表达式或注解匹配方法签名）。
 * 这种功能超出了 aopalliance 的设计范围，因为它只是一个基础接口库，而不是一个完整的 AOP 实现框架。
 *
 * 更高级的功能（如 Pointcut 定义）通常由基于 aopalliance 的框架（如 Spring AOP 或 AspectJ）来实现。
 *
 */
public class A01PointCutDemo {

	/**  过滤模式
	 *  ClassFilter
	 *  判断模式
	 * MethodMatcher

	 */

	public static void main(String[] args) {


		EchoServiceEchoMethodPointcut pointcut = new EchoServiceEchoMethodPointcut();

		// 将 Pointcut 适配成 Advisor  pointcut 无法直接被使用，只是一个过滤判断标准，需要通过Advisor进行转换
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

	/**
	 *  JoinPoint 连接点 spring中方法是链接点，他的实现是可以进行方法执行的
	 *
	 *  pointCut 切入点，是一个过滤器，针对类和方法的过滤，一个方法要执行时，通过切入点进行过滤，找到适配的Advice
	 *
	 *  然后Advice 会被进行封装成对应的 cglib 中的methodInterceptor
	 *  然后进行代理的执行，最后进行 jointpoint 的执行
	 *
	 *
	 *
	 * ：aopalliance 并没有违反 AOP 的核心思想。它的设计目标是提供一个基础接口集合，而不是一个完整的 AOP 实现框架。
	 * 职责明确：aopalliance 只关注 AOP 的基础部分（如 Advice 和 Joinpoint），
	 * 而将更复杂的概念（如 Aspect 和 Pointcut）留给更高层的框架去实现。这种设计使得 aopalliance 可以被不同的 AOP 框架灵活使用。
	 *
	 *
	 * aopalliance 的设计初衷是为了提供一个轻量级的基础接口集合，而不是一个完整的 AOP 实现框架。
	 * 因此，它只定义了最基本的 AOP 元素（如 Advice 和 Joinpoint），而将更复杂的概念（如 Aspect 和 Pointcut）留给更高层的框架去实现
	 *
	 */


}
