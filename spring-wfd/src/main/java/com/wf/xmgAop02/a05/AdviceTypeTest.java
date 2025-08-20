package com.wf.xmgAop02.a05;

public class AdviceTypeTest {
	/**
	 *  spring的 AOP advice类型
	 *
	 *  @see org.aopalliance.aop.Advice
	 *  advice 使用的是Aop 联盟中的接口，org.aopalliance.aop.Advice
	 *  spring对AOP联盟中的标准进行了扩展，新增了前置通知，后置返回通知，后置异常通知 后置最终通知 环绕通知， 并且针对这些产生了对应的方法通知
	 *
	 * @see org.springframework.aop.BeforeAdvice
	 * @see org.springframework.aop.AfterAdvice
	 * @see org.springframework.aop.ThrowsAdvice
	 *
	 *  定义扩展之后，又针对方法进行了定义
	 * @see org.springframework.aop.MethodBeforeAdvice (继承自 BeforeAdvice)
	 * @see org.springframework.aop.AfterReturningAdvice (继承自 AfterAdvice)
	 *
	 *  为什么没有 ThrowsAdvice针对方法的定义呢？ 看 ThrowsAdvice的注释，可以知道，他会针对五个方法的模式进行处理
	 *
	 *
	 *  BeforeAdvice 只是一个标记接口，表示是一个拦截动作
	 *  MethodBeforeAdvice 则是具象到对应的method上，
	 *
	 *
	 *  AfterAdvice
	 *   AfterReturningAdvice
	 *   ThrowsAdviceInterceptor
	 *
	 *AOPProxy接口返回代理对象 (创建代理对象的工厂)
	 *
	 * spring Aop中
	 * 三种业务场景实现 ProxyFactory ProxyFactoryBean AspectJProxyFactory
	 * 一种aop代理工厂 AopProxyFactory
	 * 两种aop代理对象 JdkDynamicAopProxy 和 CglibAopProxy
	 *

	 * aop proxy 三种实现
	 *
	 * 产生的业务代理对象两种，jdk动态代理，cglib动态代理
	 *
	 *
	 */
}
