package com.wf.xmgAop03.a01;

/**
 * 43 接入点 joinPoint 接口
 *
 *  主要查看类结构
 *  org.aopalliance.intercept.Joinpoint (joinPoint的抽象顶层接口)
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

// // aspect  没有进行高级抽象 Joinpoint  pointcut 则进行了高级抽象，有对应的类进行处理
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




}
