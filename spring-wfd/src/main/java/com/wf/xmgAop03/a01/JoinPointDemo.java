package com.wf.xmgAop03.a01;

/**
 * 43 接入点 joinPoint 接口
 *
 *  主要查看类结构
 *  org.aopalliance.intercept.Joinpoint (joinPoint的抽象顶层接口)
 *
 * @see org.aopalliance.intercept.Joinpoint //(这个是属于aop alliance aop联盟中的)
 *
 *  实现
 *  Interceptor 执行上下文 -Invocation
 *  - 方法拦截器执行上下文 - MethodInvocation
 *  - 构造器拦截器执行上下文 - ConstructorInvocation (aspectJ中存在实现,可以进行构造器拦截，spring不行)
 *
 *  MethodInvocation 实现
 *  - 基于反射的 - ReflectiveMethodInvocation  (jdk反射)
 *  - 基于cglib - CglibMethodInvocation
 *
 */

// // aspect  没有进行高级抽象 Joinpoint,  pointcut 则进行了高级抽象，有对应的类进行处理
// 接入点接口 joinpoint
// joinpoint 设计 看类的设计
public class JoinPointDemo {

	/**
	 * Interceptor执行上下文  Invocation
	 * 方法拦截器执行上下文 methodInvocation   ReflectiveMethodInvocation   CglibMethodInvocation
	 *
	 *
	 * 	 * JoinPoint
	 * 	 *    --Invocation
	 * 	 *    	-- MethodInvocation （构造器的不支持）
	 * 	 *    		-- ProxyMethodInvocation
	 * 	 *    			-- ReflectiveMethodInvocation
	 * 	 *    				-- CglibMethodInvocation
	 *
	 */


	/**
	 * AOP Alliance 和 AspectJ 都是 AOP（面向切面编程）领域的重要标准/实现，但它们有不同的定位和设计目标
	 *
	 *
	 * aop Alliance 规范
	 * 只定义了规范 ，运行时代理 ，仅支持方法拦截，
	 * 是一个由多个Java AOP框架共同制定的接口标准
	 * 目标是为不同AOP实现提供通用API
	 * Spring Framework 完全实现了 AOP Alliance 规范，并以此为基础构建了自己的AOP体系，进行了一些扩展
	 *
	 * <dependency>
	 *       <groupId>aopalliance</groupId>
	 *       <artifactId>aopalliance</artifactId>
	 *     </dependency>
	 *
	 *
	 * AspectJ框架
	 * 是一个完整的AOP实现 ， 有自己的语法（注解和AJDT语法） Spring通过@AspectJ注解支持与之集成
	 *
	 *
	 * Spring AOP 使用 AOP Alliance 接口作为核心抽象， 通过集成 AspectJ 来增强功能
	 *
	 *
	 * spring 通过cglib与Jdk动态代理技术，产生对应的动态代理对象，然后再进行回调业务增强的时候，进行了AOP逻辑的处理
	 *
	 * 在处理AOP的时候，主要整合了AopAlliance 和 AspectJ ,扩展了AopAlliance,适配了AspectJ
	 */



}
