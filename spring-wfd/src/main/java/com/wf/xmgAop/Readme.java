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
	 *    	-- MethodInvocation （代理方法的）
	 *    		-- ProxyMethodInvocation
	 *    			-- ReflectiveMethodInvocation(基于反射)
	 *    			-- CglibMethodInvocation(基于cglib)
	 *
	 *
	 * pointCut
	 * 		-- StaticMethodMatcherPointcut
	 *
	 * Adivce  分为三类
	 * 		--Interceptor(类似AroundAdvice)
	 * 			--MethodInterceptor 方法存在参数 MethodInvocation，关联到JoinPoint
	 * 		--BeforeAdvice
	 * 			-- MethodBeforeAdvice
	 * 		--AfterAdvice
	 * 			-- AfterReturningAdvice
	 * 			-- ThrowsAdvice
	 *
	 *
	 * Advisor
	 * 		--PointcutAdvisor
	 * 			--DefaultPointCutAdvisor
	 * 		-- IntroductionAdvisor
	 * 			--DefaultIntroductionAdvisor
	 *
	 *
	 * org.aopalliance.intercept.Interceptor(继承了Advice)
	 * 	-- org.aopalliance.intercept.MethodInterceptor
	 * 		--DynamicAdvisedInterceptor
	 *
	 *
	 * org.springframework.cglib.proxy.Callback（重新写了Cglib的相关）
	 *	--org.springframework.cglib.proxy.MethodInterceptor
	 *		-- org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor (CglibAopProxy中的 Enhancer的处理callBack)
	 *
	 *
	 *
	 * org.springframework.cglib.proxy.MethodInterceptor , 这个是基于cglib的拦截器，是CGLIB库的一部分，专门用于基于子类化的代理实现，Spring内部使用的CGLIB代理专用接口
	 * org.aopalliance.intercept.MethodInterceptor ，来自AOP Alliance（一个AOP标准化组织）的标准接口 ，定义了一个通用的AOP拦截器契约，只关注方法拦截的核心逻辑（invoke(MethodInvocation)方法）
	 *
	 * Spring内部通过CglibAopProxy等机制将AOP Alliance的MethodInterceptor适配到CGLIB的MethodInterceptor上，使得开发者通常只需要关心AOP Alliance的标准接口
	 *
	 * Spring最初使用AOP Alliance的接口作为标准AOP抽象，后来为了支持CGLIB代理，需要与CGLIB库集成。CGLIB本身有自己的MethodInterceptor接口，Spring保留了这两种接口以保持兼容性。
	 * Spring 为了使用Cglib ，进行了 Cglib 的重新打包，变成了spring-cglib-repack的jar包。Enhancer的都进行了包名替换。
	 *
	 *
	 *
	 *
	 * AdvisorAdapter （进行Advice是否支持的判断，然后如果在支持的情况下，转换为methodInterceptor，进行Advisor转换methodInterceptor）
	 * 		-- AfterReturningAdviceAdapter
	 * 		-- MethodBeforeAdviceAdapter
	 * 		-- ThrowsAdviceAdapter
	 *
	 * AdvisorAdapterRegistry (通过注册类进行注册Adapter)
	 * 		--DefaultAdvisorAdapterRegistry
	 *
	 *
	 * AopProxy
	 * 		--jdkDynamicAopProxy
	 * 	 	--CglibAopProxy
	 *
	 *
	 *
	 * AdvisedSupport （父类 ProxyConfig Advised,aop的配置信息）
	 * 		--ProxyCreatorSupport
	 * 			--proxyFactory
	 * 			--proxyFactoryBean
	 * 			--AspectJProxyFactory
	 *
	 *
	 *AdvisorChainFactory
	 * 		-- DefaultAdvisorChainFactory 唯一实现  (存储了我们的MethodInterceptor)
	 *
	 *
	 * AbstractAdvisorAutoProxyCreator(自动动态代理相关)
	 * 		-- BeanNameAutoProxyCreator
	 * 	    -- DefaultAdvisorAutoProxyCreator
	 * 	    -- AnnotationAwareAspectJAutoProxyCreator
	 *
	 *
	 * TargetSource (AOP代理目标对象来源)
	 * 		--AbstractPrototypeBasedTargetSource
	 * 			-- PrototypeTargetSource
	 * 		--SingletonTargetSource
	 *
	 *
	 *
	 *
	 * 49
	 *
	 * Aop代理对象
	 * 	Aop代理工厂 AopProxyFactory ---> DefaultProxyFactory
	 * 	Aop代理对象 AopProxy JdkDynamicAopProxy
	 *
	 *
	 * 63
	 * Advisor 与 Advice 的关系  1:1关系
	 *
	 * MethodInterceptor 与 AdvisorAdapter的关系   适配关系
	 *
	 * Advosor 与 AdvisorAdapter的关系  关联关系
	 *
	 * Aop代理对象 与 Aop代理配置  配置管理关系
	 *
	 * AdvisorChainFactory 与 MethodInterceptor 存储关系
	 *
	 *
	 *
	 */
}
