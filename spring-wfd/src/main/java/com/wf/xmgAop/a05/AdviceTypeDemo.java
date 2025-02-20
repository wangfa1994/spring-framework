package com.wf.xmgAop.a05;

public class AdviceTypeDemo {
	/**
	 *  spring的 AOP advice类型
	 *
	 *  BeforeAdvice 只是一个标记接口，表示是一个拦截动作
	 *  MethodBeforeAdvice 则是具象到对应的method上，
	 *
	 *  AfterAdvice
	 *   AfterReturningAdvice
	 *   ThrowsAdviceInterceptor
	 *
	 *AOPProxy接口返回代理对象
	 *
	 * spring aop的三种实现
	 *
	 * 基于jdk动态代理实现   JdkDynamicAopProxy 内部类
	 * cglib 动态代理实现-基于类代理字节码提升  CglibAopProxy  内部类
	 *
	 * aspectJ适配实现- AspectJProxyFactory
	 *	利用了aspectJ的注解，解析他的注解，用spring的方式去处理
	 * 没有使用aspectJ的编译器等
	 *
	 *
	 *
	 *
	 */
}
