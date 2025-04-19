package com.wf.xmgAop02.a05;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class AdviceTypeDemo {
	/**
	 *  spring的 AOP advice类型
	 *
	 *  @see org.aopalliance.aop.Advice
	 *  advice 使用的是Aop 联盟中的接口，org.aopalliance.aop.Advice
	 *  spring对AOP联盟中的标准进行了扩展，新增了前置通知，后置返回通知，后置异常通知 后置最终通知 环绕通知
	 *
	 * @see org.springframework.aop.BeforeAdvice
	 * @see org.springframework.aop.AfterAdvice
	 * @see org.springframework.aop.AfterReturningAdvice (继承自 AfterAdvice)
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
	 *
	 * spring aop的三种实现 代理对象的三种实现AopProxy
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
