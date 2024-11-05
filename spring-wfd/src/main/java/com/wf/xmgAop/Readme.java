package com.wf.xmgAop;

public class Readme {

	/**
	 * oop面相对象编程
	 * aop面向切面编程
	 *
	 * 基本类 CLass类 Method类
	 * 字节码提升 ClassVisitor
	 *
	 * oop 封装  继承  多态
	 *
	 * java 属于静态语言，类结构一旦被定义就很难被改变，想要修改或者扩展就特别困难
	 *虽然可以通过classLoad生成新的字节码，但是这种方式太过复杂与困难
	 *
	 *
	 * MDC
	 *
	 * AOP定义 拦截oop的方法字段等
	 *
	 * aop是辅助oop在不改变原有逻辑的情况下，进行增加业务
	 *
	 * Aspect概念  是一个组织的形式
	 *
	 * joint point概念 是拦截的方法
	 *
	 * point cut 概念 条件过滤join point
	 *
	 * Advice概念 通知  在那个通知，主动通知，被动通知 around 只拦截，不调用目标方法
	 *
	 * introduction概念
	 *
	 *
	 * 通过静态代理或者动态代理产生对象之后，判断在什么模式下进行前/后置等的拉结、
	 *
	 * 静态代理，包括 oop继承 和 组合模式
	 *
	 * ProxyFactoryBean
	 * ProxyFactory
	 * AspectJProxyFactory
	 *
	 *
	 *
	 * spring aop api
	 *
	 * joinPoint
	 *    --Invocation
	 *    	-- MethodInvocation （构造器的不支持）
	 *    		-- ProxyMethodInvocation
	 *    			-- ReflectiveMethodInvocation
	 *    				-- CglibMethodInvocation
	 *
	 *
	 * pointCut
	 * 		-- StaticMethodMatcherPointcut
	 *
	 * Adivce
	 * 		--interceptor(类似AroundAdvice)
	 * 			--methodInterceptor
	 * 		--BeforeAdvice
	 * 			-- MethodBeforeAdvice
	 * 		--AfterAdvice
	 * 			-- AfterReturningAdvice
	 * 			-- ThrowsAdvice
	 *
	 * advisor
	 * 		--PointcutAdvisor
	 * 			--DefaultPointCutAdvisor
	 *
	 * AdvisedSupport
	 * 		--ProxyCreatorSupport
	 * 			--proxyFactory
	 * 			--proxyFactoryBean
	 * 			--AspectJProxyFactory
	 *
	 * AbstractAdvisorAutoProxyCreator
	 * 		-- BeanNameAutoProxyCreator
	 * 	    -- DefaultAdvisorAutoProxyCreator
	 * 	    -- AnnotationAwareAspectJAutoProxyCreator
	 *
	 *
	 * TargetSource
	 *
	 *
	 *
	 *
	 *
	 */
}
